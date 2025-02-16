package org.halilkrkn;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Set<Integer> uniqueNumbers = new LinkedHashSet<>();
        List<Integer> numbersList = new ArrayList<>();

        // 500 benzersiz sayı elde edilene kadar döner.
        while (uniqueNumbers.size() < 500) {
            int num = random.nextInt(10000) + 1; // 1 ile 10.000 arasında
            if (uniqueNumbers.add(num)) {
                numbersList.add(num);
                normalizeAndPrint(numbersList);
            }
        }
    }

    // Listeye eklenen her eleman sonrası normalizasyonu hesaplar ve yazdırır.
    private static void normalizeAndPrint(List<Integer> list) {
        int xmin = Collections.min(list);
        int xmax = Collections.max(list);
        System.out.println("Veri Seti: " + list);
        System.out.print("Normalizasyon: [");
        for (int i = 0; i < list.size(); i++) {
            int normalized = (xmax == xmin) ? 0 : (int) (list.get(i) - xmin) / (xmax - xmin);
            System.out.print(normalized);
            if (i < list.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("-------------------------------------");
    }
}