abstract class Item {
    protected String nome;
    protected double valorBase;

    public Item(String nome, double valorBase) {
        this.nome = nome;
        this.valorBase = valorBase;
    }

    public abstract double calcularImposto();

    public String getNome() {
        return nome;
    }

    public double getValorBase() {
        return valorBase;
    }
}

class Produto extends Item {
    public Produto(String nome, double valorBase) {
        super(nome, valorBase);
    }

    @Override
    public double calcularImposto() {
        double icms = new ICMS().calcular(this.valorBase);
        double ipi = new IPI().calcular(this.valorBase);
        return icms + ipi;
    }
}

class Servico extends Item {
    public Servico(String nome, double valorBase) {
        super(nome, valorBase);
    }

    @Override
    public double calcularImposto() {
        double iss = new ISS().calcular(this.valorBase);
        double icms = new ICMS().calcular(this.valorBase);
        return iss + icms;
    }
}

interface Imposto {
    double calcular(double valorBase);
}

class ISS implements Imposto {
    @Override
    public double calcular(double valorBase) {
        return valorBase * 0.073;
    }
}

class ICMS implements Imposto {
    @Override
    public double calcular(double valorBase) {
        return valorBase * 0.132;
    }
}

class IPI implements Imposto {
    @Override
    public double calcular(double valorBase) {
        return valorBase * 0.219;
    }
}

class OperacaoComercial {
    private Item item;

    public OperacaoComercial(Item item) {
        this.item = item;
    }

    public void calcularTotal() {
        double imposto = item.calcularImposto();
        double valorTotal = item.getValorBase() + imposto;

        System.out.println("Item: " + item.getNome());
        System.out.println("Valor Base: R$ " + item.getValorBase());
        System.out.println("Imposto: R$ " + imposto);
        System.out.println("Valor Total: R$ " + valorTotal);
    }
}

public class Main {
    public static void main(String[] args) {
        Produto produto = new Produto("Computador Gamer", 6300.00);
        Servico servico = new Servico("Consultoria", 800.00);

        OperacaoComercial operacaoProduto = new OperacaoComercial(produto);
        OperacaoComercial operacaoServico = new OperacaoComercial(servico);

        System.out.println("Operação com Produto:");
        operacaoProduto.calcularTotal();

        System.out.println("\nOperação com Serviço:");
        operacaoServico.calcularTotal();
    }
}
