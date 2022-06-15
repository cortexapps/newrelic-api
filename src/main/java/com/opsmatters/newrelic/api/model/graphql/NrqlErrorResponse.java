package com.opsmatters.newrelic.api.model.graphql;

import java.util.List;

public class NrqlErrorResponse extends NrqlQueryResponse {

    private List<Error> errors;

    public NrqlErrorResponse() {
        super(false);
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public class Error {

        private List<Location> locations;
        private String message;

        public List<Location> getLocations() {
            return locations;
        }

        public void setLocations(List<Location> locations) {
            this.locations = locations;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public class Location {

        private int column;
        private int line;

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }
    }
}
