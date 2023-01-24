package vr.miniautorizador.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Transaction {

    @JsonProperty("numeroCartao")
    private String cardNumber;
    @JsonProperty("senhaCartao")
    private String password;
    @JsonProperty("valor")
    private double value;

    public Transaction() {
    }

    public Transaction(String cardNumber, String password, double value) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.value = value;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
