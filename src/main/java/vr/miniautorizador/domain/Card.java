package vr.miniautorizador.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Card {

    @Id
    @JsonProperty("numeroCartao")
    private String cardNumber;

    @JsonIgnore
    @JsonProperty("saldo")
    private double balance;
    @JsonProperty("senha")
    private String password;

    public Card() {
        this.balance = 500;
    }

    public Card(String cardNumber, String password) {
        this.cardNumber = cardNumber;
        this.balance = 500;
        this.password = password;
    }

    public Card(String cardNumber, String password, double balance) {
        this.cardNumber = cardNumber;
        this.balance = 500;
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}