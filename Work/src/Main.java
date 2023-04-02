import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Worker names[];
    static int length;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество объектов Worker: ");
        Worker[] workers = new Worker[scanner.nextInt()];

        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker();
            System.out.print(i + 1 + ". Введите фамилию и инициалы: ");
            scanner.nextLine();
            workers[i].setInitials(scanner.nextLine());
            System.out.print("   Введите название должности: ");
            workers[i].setJob(scanner.nextLine());
            System.out.print("   Введите год поступления: ");
            workers[i].setSYear(scanner.nextShort());
            System.out.println();
        }
        sort(workers);
        System.out.println("Записи упорядоченные по алфавиту: ");
        for (int i = 0; i < workers.length; i++) {
            System.out.println("Фамилия и инициалы: " + workers[i].getInitials());
            System.out.println("Должость: " + workers[i].getJob());
            System.out.println("Год поступления: " + workers[i].getSYear());
            System.out.println();
        }
        System.out.println("Информация о работниках, чей стаж превышает введдённое значение: ");
        int stage = 0;
        boolean anybody;
        while ((stage = scanner.nextInt()) > 0){
            anybody = false;
            for (Worker tmp : workers) {
                if(2023 - tmp.getSYear() > stage){
                    anybody = true;
                    System.out.println(tmp.getInitials());
                    System.out.println(tmp.getJob());
                    System.out.println();
                }
            }
            if(!anybody) System.out.println("Таких работников нет! \n");
        }
    }


    static void sort(Worker array[]) {
        if (array == null || array.length == 0) {
            return;
        }
        names = array;
        length = array.length;
        quickSort(array, 0, length - 1);
    }

    static void quickSort(Worker[] array, int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        String pivot = names[lowerIndex + (higherIndex - lowerIndex) / 2].getInitials();

        while (i <= j) {
            while (names[i].getInitials().compareToIgnoreCase(pivot) < 0) {
                i++;
            }

            while (names[j].getInitials().compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                exchangeNames(i, j);
                i++;
                j--;
            }
        }
        //call quickSort recursively
        if (lowerIndex < j) {
            quickSort(array, lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(array, i, higherIndex);
        }
    }

    static void exchangeNames(int i, int j) {
        Worker temp = names[i];
        names[i] = names[j];
        names[j] = temp;
    }
}