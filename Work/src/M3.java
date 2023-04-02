public class M3 {
    public static void main(String[] args) {
        People[] people = {new People("Taganrog"), new People("Komarovo"),
                new People("Taganrog"), new People("Moscow"), new People("Moscow"),
                new People("Moscow")};

        Train[] trains = {new Train(2, "Komarovo"), new Train(2, "Moscow"),
                new Train(2, "Taganrog")};
        Station station = new Station(trains, people);
    }
}
