package br.com.verex.avaliacao.ldap;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        final ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 4, 5, 5, 5, 5, 5);
            final AtomicInteger counter = new AtomicInteger(0);
        final Collection<List<Integer>> chunk  = integers
                .stream()
                .collect(
                        Collectors
                                .groupingBy(it -> counter.getAndIncrement() / 3)
                )
                .values();

        chunk
                .stream()
                .forEach(it-> System.out.println(it));


        Double venda = 1000.00;
        Funcionario funcionario = new Funcionario();

        funcionario.setCargo(CargoEnum.DESENVOLVEDOR_SR);
        final Double aDouble = funcionario.getCargo().calcularComissao(venda);
        System.out.println(aDouble);
    }

}
