package com.app.appbookapi.Services;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ApiResponse {

    private List<Doc> docs;

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public static class Doc {
        private String title;

        @JsonProperty("first_publish_year")
        private String firstPublishYear;

        private String availability;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirstPublishYear() {
            return firstPublishYear;
        }

        public void setFirstPublishYear(String firstPublishYear) {
            this.firstPublishYear = firstPublishYear;
        }

        public String getAvailability() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability = availability;
        }
    }

    // Método para obtener el primer libro encontrado
    public Doc getFirstDoc() {
        return docs != null && !docs.isEmpty() ? docs.get(0) : null;
    }
}
