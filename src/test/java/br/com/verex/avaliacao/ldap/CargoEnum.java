package br.com.verex.avaliacao.ldap;



public enum CargoEnum implements CargoStrategy {

    DESENVOLVEDOR_JR(10){
        @Override
        public Double calcularComissao(Double salario) {
            return salario * 1.10;
        }
    },
    DESENVOLVEDOR_SR(20){
        @Override
        public Double calcularComissao(Double salario) {
            return salario * 1.20;
        }
    };

    CargoEnum(int i) {
    }
}
