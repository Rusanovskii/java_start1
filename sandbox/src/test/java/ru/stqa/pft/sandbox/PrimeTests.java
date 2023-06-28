package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
    @Test
    public void testPrime(){
        // выполняется большое количество итераций для проверки простого числа
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }
    @Test
    public void testPrimeFast() {
        // выполняется большое количество итераций для проверки простого числа
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }
    @Test
    public void testPrimeMoreFast() {
        // выполняется большое количество итераций для проверки простого числа
        Assert.assertTrue(Primes.isPrimeMoreFast(Integer.MAX_VALUE));
    }
    @Test
    public void testNonPrime(){
        // выполняется -//- не простого числа; выполняется в 10 раз быстрее
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }

    @Test(enabled = false)
    public void testPrimeLong(){
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }
}
