import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.JOptionPane;

// Koordenatuak klasea, Gradu, minutu eta segundoak gordetzeko klasea.
class Koordenatuak {
    private int gradua;
    private int minutuak;
    private float segundoak;

    // Eraikitzailea, Koordenatuak objektua sortzen du.
    public Koordenatuak(int gradua, int minutuak, float segundoak) {
        this.gradua = gradua;
        this.minutuak = minutuak;
        this.segundoak = segundoak;
    }

    // Getter-ak, Koordenatuak objektuaren atributuak irakurri ahal izateko.
    public int getGradua() {
        return gradua;
    }

    public int getMinutuak() {
        return minutuak;
    }

    public float getSegundoak() {
        return segundoak;
    }

    // Koordenatuak objektua testu eran adierazteko.
    public String toString() {
        return gradua + "º " + minutuak + "' " + segundoak + "\"";
    }
}

// Meteoro klasea, Meteoroen informazioa gordetzeko klasea.
class Meteoro {
    private String izena;
    private String data;
    private Koordenatuak latitudea;
    private Koordenatuak longitudea;
    private String besteDatuBat;

    // Eraikitzailea: Meteoro objektua sortzen du.
    public Meteoro(String izena, String data, Koordenatuak latitudea, Koordenatuak longitudea, String besteDatuBat) {
        this.izena = izena;
        this.data = data;
        this.latitudea = latitudea;
        this.longitudea = longitudea;
        this.besteDatuBat = besteDatuBat;
    }

    // Getter-ak, Meteoro objektuaren atributuak irakurri ahal izateko.
    public String getIzena() {
        return izena;
    }

    public String getData() {
        return data;
    }

    public Koordenatuak getLatitudea() {
        return latitudea;
    }

    public Koordenatuak getLongitudea() {
        return longitudea;
    }

    public String getBesteDatuBat() {
        return besteDatuBat;
    }

    // toString() metodoa, Meteoro objektua testu eran adierazteko.
    public String toString() {
        return "Meteoro: " + izena + " - Data: " + data + " - Latitudea: " + latitudea + " - Longitudea: " + longitudea;
    }
}

// BakarkakoZatia klasea, Meteoro objektuak kudeatzeko programa nagusia.
public class BakarkakoZatia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Meteoro> meteoros = new ArrayList<>();

        // Programa infinitu bitartean exekutatzen da.
        while (true) {
            // Aukerak erakusten dituen testu mezuak.
            String aukerak = "Aukeratu aukera bat:\n" +
                             "1. Gehitu meteoro berria\n" +
                             "2. Ezabatu meteoroa\n" +
                             "3. Ordenatu meteoroak\n" +
                             "4. Irten";

            // Aukera zenbakia hartu eta zenbakia irakurri.
            String input = JOptionPane.showInputDialog(null, aukerak);
            int aukera = Integer.parseInt(input);

            // Aukeren arabera kodea exekutatu.
            switch (aukera) {
                case 1:
                    // Meteoroa gehitzeko aukera.
                    String izena = JOptionPane.showInputDialog("Izena:");
                    String data = JOptionPane.showInputDialog("Data (UUUU-HH-EE):");
                    Koordenatuak latitudea = irakurriKoordenatuak("Latitudea");
                    Koordenatuak longitudea = irakurriKoordenatuak("Longitudea");
                    String besteDatuBat = JOptionPane.showInputDialog("Beste datu bat:");

                    Meteoro meteoro = new Meteoro(izena, data, latitudea, longitudea, besteDatuBat);
                    meteoros.add(meteoro);
                    break;
                case 2:
                    // Meteoroa ezabatzeko aukera.
                    if (!meteoros.isEmpty()) {
                        StringBuilder meteoroInfo = new StringBuilder("Aukeratu meteoroa ezabatzeko zenbakia:\n");
                        for (int i = 0; i < meteoros.size(); i++) {
                            meteoroInfo.append(i).append(". ").append(meteoros.get(i)).append("\n");
                        }

                        int zenbakia = Integer.parseInt(JOptionPane.showInputDialog(meteoroInfo.toString()));
                        if (zenbakia >= 0 && zenbakia < meteoros.size()) {
                            meteoros.remove(zenbakia);
                            JOptionPane.showMessageDialog(null, "Meteoroa ezabatuta.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Zenbakia okerra.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ez dago meteororik ezabatzeko.");
                    }
                    break;
                case 3:
                    // Meteorosak data arabera ordenatu eta inprimatu.
                    if (!meteoros.isEmpty()) {
                        Collections.sort(meteoros, new Comparator<Meteoro>() {
                            public int compare(Meteoro m1, Meteoro m2) {
                                return m1.getData().compareTo(m2.getData());
                            }
                        });

                        StringBuilder orderedMeteoros = new StringBuilder("Meteoros ordenatuta data arabera:\n");
                        for (Meteoro m : meteoros) {
                            orderedMeteoros.append(m).append("\n");
                        }

                        JOptionPane.showMessageDialog(null, orderedMeteoros.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Ez dago meteororik ordenatzeko.");
                    }
                    break;
                case 4:
                    // Irten aukera aukeratzen denean.
                    JOptionPane.showMessageDialog(null, "Agur!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    // Aukera okerra aukeratzean.
                    JOptionPane.showMessageDialog(null, "Aukera okerra.");
                    break;
            }
        }
    }

    // Koordenatuak irakurriKoordenatuak metodoa, Koordenatuak irakurtzen ditu.
    private static Koordenatuak irakurriKoordenatuak(String koordenatua) {
        int gradua = Integer.parseInt(JOptionPane.showInputDialog(koordenatua + " - Graduak:"));
        int minutuak = Integer.parseInt(JOptionPane.showInputDialog(koordenatua + " - Minutuak:"));
        float segundoak = Float.parseFloat(JOptionPane.showInputDialog(koordenatua + " - Segundoak:"));

        return new Koordenatuak(gradua, minutuak, segundoak);
    }
}