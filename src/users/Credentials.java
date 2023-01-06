package users;

public class Credentials {
    private String name;
    private String password;
    private String accountType = "standard";
    private String country;
    private String balance;


    /**
     *
     * @return name of user
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name to set for user
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password to set for user
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     *
     * @return account type of user
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     *
     * @param accountType to set for user
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     *
     * @return country of user
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country to set for user
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     *
     * @return balance of user
     */
    public String getBalance() {
        return balance;
    }

    /**
     *
     * @param balance to set for user
     */
    public void setBalance(final String balance) {
        this.balance = balance;
    }
}
