package xyz.raieen.cardsapp;

import java.util.List;

public class UserAccount {

    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String accountType;
    private long created;
    private List<String> cards;

    public UserAccount() {
    }

    public UserAccount(String id, String email, String password, String firstName, String lastName, String accountType, long created, List<String> cards) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
        this.created = created;
        this.cards = cards;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAccountType() {
        return accountType;
    }

    public long getCreated() {
        return created;
    }

    public List<String> getCards() {
        return cards;
    }
}
