/*

Daubner Sebastian
2BHIT
Aufgabe: 01

 */


import java.util.Scanner;

public class Main {
    public static double calcMaxProfit(String[][] list) {
        double sum = 0.00;

        for (int i = 0; i <list.length; i++) {
            sum += (Double.parseDouble(list[i][2]) * Double.parseDouble(list[i][3]));
        }
        return sum;
    }

    public static String findMostSeats(String[][] list) {
        int highestSeats = Integer.MIN_VALUE;
        String filmName = "";

        for (int i = 0; i < list.length; i++) {

            if (Integer.parseInt(list[i][2]) > highestSeats) {
                highestSeats = Integer.parseInt(list[i][2]);
                filmName = list[i][0];
            }
        }

        return filmName + "%" + highestSeats;
    }

    public static int findGenre(String[][] list, String name) {
        int existierendeFilmeCounter = 0;

        for (int i = 0; i < list.length; i++) {
            if (list[i][1].equals(name)) {
                existierendeFilmeCounter++;
            }
        }
        return existierendeFilmeCounter;
    }

    public static String getFilmData(String[][] list, String movieName, int ticketAnzahl) {
        boolean movieVorhanden = false, ticketKaufMöglich = false;
        int movieValue = Integer.MAX_VALUE;

        for (int i = 0; i < list.length; i++) {
            if (list[i][0].equals(movieName)) {
                movieVorhanden = true;

                movieValue = Integer.parseInt(list[i][2]);

            if (movieValue > ticketAnzahl) {
                    ticketKaufMöglich = true;
                }
            }
        }

        // SUB List Eintrag
        movieValue = movieValue - ticketAnzahl;

        return movieVorhanden + "%" + ticketKaufMöglich + "%" + movieValue;

    }

    public static void main(String[] args) {
        String[][] kino = {
                {"Avengers", "Action", "45", "9.50"},
                {"Frozen", "Animation", "60", "7.00"},
                {"Joker", "Drama", "20", "8.80"},
                {"Minions", "Animation", "55", "6.90"},
                {"Top Gun", "Action", "10", "9.00"},
                {"The Notebook", "Romantik", "30", "7.50"}
        };

        // Aufgabe 1
        double maxProfit = calcMaxProfit(kino);
        System.out.printf("Max. Profit: %.2f €", maxProfit);

        System.out.println();

        // Aufgabe 2
        String[] filmMitMeistenSitzen = findMostSeats(kino).split("%"); // zeichen zum parsern verwenden welches wohl kaum vorkommen wird.
        System.out.printf("'%S' mit %d Plätzen", filmMitMeistenSitzen[0], Integer.parseInt(filmMitMeistenSitzen[1]));

        // Aufgabe 3
        String inputString = "";
        Scanner scan = new Scanner(System.in);

        System.out.println();

        do {
            System.out.print("Welches Gerne wollen Sie ansehen?: ");
            inputString = scan.next();
        } while (inputString.length() <= 4);

        int anzahlFilme = findGenre(kino, inputString);
        System.out.printf("Anhand dieses Suchergebnises konnte " +
                "folgende Anzahl an Filmgenre " +
                "gefunden werden: %d", anzahlFilme);

        System.out.println();

        // Aufgabe 4
        int inputInt = 0;

        do {
            System.out.print("Welchen Film wollen Sie ansehen?: ");
            inputString = scan.next();
        } while (inputString.length() <= 4);

        do {
            System.out.print("Wieviele Tickets wollen Sie gerne kaufen?: ");
            inputInt = scan.nextInt();
        } while (inputInt <= 0);

        String[] data = getFilmData(kino, inputString, inputInt).split("%");

        if (!Boolean.parseBoolean(data[0])) {
            System.out.printf("Der von Ihnen eingetippte Film existiert nicht!");
        }
        else if (!Boolean.parseBoolean(data[1])) {
            System.out.printf("Tickets nicht verfügbar!" +
                    "\nTicketanzahl zu hoch, oder keine Tickets mehr verfügbar!\n");
        }
        else {
            System.out.printf("Alles war in Ordnung, Film ist nun gebucht!\n");
        }

        System.out.printf("Ticketanzahl nun noch möglich: %S", data[2]);

        // Aufgabe 5
        if (Integer.parseInt(data[2]) < 15) {
            System.out.println("\nWeniger als 15 freie Tickets vorhanden!");
        }
    }
}