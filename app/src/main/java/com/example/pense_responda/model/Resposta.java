package com.example.pense_responda.model;

public class Resposta {
    private String resposta;
    private String id;

    public Resposta() {}

    public Resposta(String resposta) {
        this.resposta = resposta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
