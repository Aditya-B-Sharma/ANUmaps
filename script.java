import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.lang.ProcessBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class script {
    public static void main(String[] args) throws IOException, InterruptedException {
        // System.out.println("Test");
        extract();
        File f = new File("C:\\Users\\Adi\\Desktop\\ANUmaps\\extractedsites.txt");
        FileOutputStream output = new FileOutputStream("C:\\Users\\Adi\\Desktop\\ANUmaps\\output.txt");

        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            String current = scanner.nextLine();
            if (current.contains("https://services.anu.edu.au/campus-environment/facilities-maps/")) {
                byte b[] = current.getBytes();
                output.write(b);
                output.write("\n".getBytes());

            }

        }
        scanner.close();
        output.close();

    }

    public static void extract() throws IOException, InterruptedException {
        System.out.println("Extracting test...");
        File c = new File("C:\\Users\\Adi\\Desktop\\ANUmaps\\sites.txt");
        FileOutputStream output = new FileOutputStream("C:\\Users\\Adi\\Desktop\\ANUmaps\\extractedsites.txt");
        ProcessBuilder pb = new ProcessBuilder();

        Scanner scanner = new Scanner(c);
        while (scanner.hasNextLine()) {
            String current = scanner.nextLine();
            System.out.println(current);
            pb.command("ubuntu.exe", "run", "lynx -listonly -nonumbers -dump '" + current + "' >> extractedsites.txt");
            Process process = pb.start();

			// blocked :(
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
            

        }
        scanner.close();
        output.close();

    }

}
