package org.halilkrkn;

public class Main {
    public static void main(String[] args) {
        int lowerPrime = findLargestPrimeBelow(500000);
        int upperPrime = findSmallestPrimeAbove(500000);
        int difference = upperPrime - lowerPrime;

        System.out.println("500.000'den küçük en büyük asal sayı: " + lowerPrime);
        System.out.println("500.000'den büyük en küçük asal sayı: " + upperPrime);
        System.out.println("İki asal sayı arasındaki fark: " + difference);
    }

    // 500.000'den küçük en büyük asal sayıyı bulan metod
    private static int findLargestPrimeBelow(int number) {
        for (int i = number - 1; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return -1; // Eğer bulunamazsa
    }

    // 500.000'den büyük en küçük asal sayıyı bulan metod
    private static int findSmallestPrimeAbove(int number) {
        int i = number + 1;
        while (true) {
            if (isPrime(i)) {
                return i;
            }
            i++;
        }
    }

    // n sayısının asal olup olmadığını kontrol eden metod
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}