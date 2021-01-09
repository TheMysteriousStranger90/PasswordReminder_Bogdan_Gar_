package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Record implements Comparable, Serializable {

    private String address;
    private String login;
    private String password;
    private Date date;

    public Record() {
    }

    public Record(String address, String login, String password, Date date) {
        this.address = address;
        this.login = login;
        this.password = password;
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Данные {" +
                " Адрес = '" + address + '\'' +
                ", Логин = '" + login + '\'' +
                ", Пароль = '" + password + '\'' +
                ", Время = '" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return address.equals(record.address) &&
                login.equals(record.login) &&
                password.equals(record.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, login, password, date);
    }

    @Override
    public int compareTo(Object o) {
        Record entry = (Record) o;
        int result = login.compareTo(entry.login);
        if (result != 0) {
            return result;
        }
        return 0;
    }
}