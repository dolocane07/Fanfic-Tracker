package com.ejemplo.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.ejemplo.model.Fanfic;

public class Ao3ScraperService {

    private static final Pattern WORK_ID_PATTERN = Pattern.compile("/works/(\\d+)");

    public Fanfic extraerFanfic(String urlOriginal) {
        String urlNormalizada = normalizarUrl(urlOriginal);
        Document documento = obtenerDocumento(urlNormalizada);

        if (!paginaPareceWork(documento)) {
            documento = obtenerDocumento(anadirVistaAdulta(urlNormalizada));
        }

        Fanfic fanfic = new Fanfic();
        fanfic.setAo3Url(urlNormalizada);
        fanfic.setAo3WorkId(extraerWorkId(urlNormalizada));
        fanfic.setTitulo(extraerTitulo(documento));
        fanfic.setAutor(extraerAutor(documento));
        fanfic.setAo3Rating(extraerTexto(documento, "dd.rating.tags li a.tag", "Sin clasificar"));
        fanfic.setWordCount(extraerEntero(documento, "dd.words", 0));
        fanfic.setWarnings(extraerTags(documento, "dd.warning.tags li a.tag"));
        fanfic.setRelationships(extraerTags(documento, "dd.relationship.tags li a.tag"));
        fanfic.setFandoms(extraerTags(documento, "dd.fandom.tags li a.tag"));
        fanfic.setCategories(extraerTags(documento, "dd.category.tags li a.tag"));

        if (fanfic.getTitulo().isBlank()) {
            throw new IllegalArgumentException("No se pudo leer el titulo del fanfic desde AO3");
        }

        return fanfic;
    }

    private String normalizarUrl(String urlOriginal) {
        if (urlOriginal == null || urlOriginal.isBlank()) {
            throw new IllegalArgumentException("La URL es obligatoria");
        }

        URI uri = URI.create(urlOriginal.trim());
        String host = uri.getHost();

        if (host == null || !host.contains("archiveofourown.org")) {
            throw new IllegalArgumentException("La URL debe pertenecer a archiveofourown.org");
        }

        Matcher matcher = WORK_ID_PATTERN.matcher(uri.getPath());
        if (!matcher.find()) {
            throw new IllegalArgumentException("La URL debe apuntar a un work de AO3");
        }

        return "https://archiveofourown.org/works/" + matcher.group(1);
    }

    private String extraerWorkId(String url) {
        Matcher matcher = WORK_ID_PATTERN.matcher(url);
        return matcher.find() ? matcher.group(1) : null;
    }

    private Document obtenerDocumento(String url) {
        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(15000)
                    .get();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo acceder a AO3", e);
        }
    }

    private boolean paginaPareceWork(Document documento) {
        return !extraerTitulo(documento).isBlank() && documento.selectFirst("dd.words") != null;
    }

    private String anadirVistaAdulta(String url) {
        return url + "?view_adult=true";
    }

    private String extraerTitulo(Document documento) {
        Element titulo = documento.selectFirst("h2.title.heading");
        return titulo != null ? titulo.text().trim() : "";
    }

    private String extraerAutor(Document documento) {
        Element autor = documento.selectFirst("a[rel=author]");
        return autor != null ? autor.text().trim() : "Autor desconocido";
    }

    private String extraerTexto(Document documento, String selector, String porDefecto) {
        Element elemento = documento.selectFirst(selector);
        return elemento != null ? elemento.text().trim() : porDefecto;
    }

    private int extraerEntero(Document documento, String selector, int porDefecto) {
        Element elemento = documento.selectFirst(selector);
        if (elemento == null) {
            return porDefecto;
        }

        String texto = elemento.text().replace(",", "").trim();
        if (texto.isBlank()) {
            return porDefecto;
        }

        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return porDefecto;
        }
    }

    private List<String> extraerTags(Document documento, String selector) {
        return documento.select(selector)
                .stream()
                .map(Element::text)
                .map(String::trim)
                .filter(texto -> !texto.isBlank())
                .distinct()
                .collect(Collectors.toList());
    }
}
