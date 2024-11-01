package ua.goit.tasks;

import java.util.Objects;

public class User {
    /*
            "id",
            "name",
            "username",
            "email",
            "address":,
            "phone",
            "website",
            "company":
     */
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }

    public void setDefaultUser(){
        /*
        {
                "id": 1,
                "name": "Leanne Graham",
                "username": "Bret",
                "email": "Sincere@april.biz",
                "address": {
                    "street": "Kulas Light",
                    "suite": "Apt. 556",
                    "city": "Gwenborough",
                    "zipcode": "92998-3874",
                    "geo": {
                        "lat": "-37.3159",
                        "lng": "81.1496"
                    }
                },
                "phone": "1-770-736-8031 x56442",
                "website": "hildegard.org",
                "company": {
                    "name": "Romaguera-Crona",
                    "catchPhrase": "Multi-layered client-server neural-net",
                    "bs": "harness real-time e-markets"
                }
        }
        */
        id = 0;
        name = "Olya";
        username = "Gorgula";
        email = "GorgulaOlya@gmail.com";

        Address address = new Address();
        address.setStreet("Shevchenko");
        address.setSuite("Apt. 108");
        address.setCity("Slavyansk");
        address.setZipcode("98998-3850");
        Geo geo = new Geo();
        geo.setLat("50.5464");
        geo.setLng("165.5274");
        address.setGeo(geo);
        this.address = address;

        phone = "3854544334345";
        website = "olya.ua";

        Company company = new Company();
        company.setName("goit");
        company.setCatchPhrase("Prog");
        company.setBs("harness real-time e-markets");
        this.company = company;
    }

}
