import java.util.*;

public class Main {
    public static void countChars(char[] arr) {
        int c = 0;
        for (char value : arr) {
            if (value != '-')
                c++;
        }
        System.out.println(c);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            // Ha az EOF jelet olvassuk, kilépünk a ciklusból
            if (line.equals("EOF")) {
                break;
            }

            // Üres sorokat figyelmen kívül hagyjuk
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            for (String part : parts) {
                int number = Integer.parseInt(part.trim());

                // Ha a szám negatív, akkor a pozitív változatát tároljuk el
                if (number < 0) {
                    number = -number;
                }

                numbers.add(number);
            }
        }
        LinkedList<Page> fifo = new LinkedList<>();
        boolean next = false;
        char[] out = new char[numbers.size()];

        Page A = new Page('A');
        Page B = new Page('B');
        Page C = new Page('C');


        A.setNumber(numbers.get(0));
        fifo.add(A);
        out[0] = 'A';

        for (int i = 1; i < numbers.size(); ++i) {
            // Végig megyünk a fifon, ha találunk benne egy olyan lapot amelyiknek megegyezik a száma akkor kilépünk.
            for(int j = 0; j < fifo.size(); ++j) {
                if(numbers.get(i) == fifo.get(j).getNumber()) {
                    out[i] = '-';
                    fifo.get(j).setFreezeTime(0);
                    fifo.get(j).setSc(true);
                    next = true;
                }
            }
            if (!next) {
                if(fifo.size() < 3) {
                    if(fifo.size() == 1) {
                        B.setNumber(numbers.get(i));
                        fifo.add(B);
                        out[i] = 'B';
                    }
                    else if(fifo.size() == 2) {
                        C.setNumber(numbers.get(i));
                        fifo.add(C);
                        out[i] = 'C';
                    }
                }else {
                    int c = 0;
                    while(c < 3) {
                        Page current = fifo.removeFirst();
                        if(!current.isSc() && !current.isFrozen()) {
                            current.setNumber(numbers.get(i));
                            current.setFrozen(true);
                            current.setSc(false);
                            current.setFreezeTime(4);
                            fifo.add(current);
                            out[i] = current.getCh();
                            break;
                        } else {
                            fifo.add(current);
                        }
                        c++;
                    }
                    if(c == 3) {
                        out[i] = '*';
                    }
                }
            }
            for (Page page : fifo) {
                page.decreaseF();
            }

            next = false;
        }
        System.out.println(out);
        countChars(out);
    }
}