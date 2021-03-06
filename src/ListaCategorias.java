import org.w3c.dom.Node;

public class ListaCategorias {

    private NodeCategoria primeiro;
    private int qtd;
    private NodeCategoria ultimo;
    // métodos da classe (inserção ordenada na lista, remoção na lista, busca na
    // lista, exibição da lista)

    public NodeCategoria getPrimeiro() {
        return primeiro;
    }

    public boolean isEmpty() {
        if (this.primeiro == null && this.qtd == 0 && this.ultimo == null) {
            return true;
        } else {
            return false;
        }
    }

    public NodeCategoria buscaMelhorada(String descricao) {
        if (this.isEmpty() != true) {
            NodeCategoria aux = this.primeiro;
            do {
                if (aux.getInfo().getDescricao().equals(descricao)) {
                    return aux;
                }
                aux = aux.getProx();
            } while (aux != this.primeiro);
        }
        return null;
    }

    public void inserirOrdenadoCrescente(NodeCategoria novoNode) {
        NodeCategoria encontrado = buscaMelhorada(novoNode.getInfo().getDescricao());
        if (encontrado == null) {
            if (this.isEmpty() == true) { // inserção na lista vazia
                novoNode.setAnt(novoNode);
                novoNode.setProx(novoNode);
                this.primeiro = novoNode;
                this.ultimo = novoNode;
                this.qtd++;
            } else if (novoNode.getInfo().compareTo(this.primeiro.getInfo()) < 0) { // inserção antes do primeiro
                NodeCategoria prox = this.primeiro;
                novoNode.setAnt(this.ultimo);
                this.ultimo.setProx(novoNode);
                novoNode.setProx(prox);
                prox.setAnt(novoNode);
                this.primeiro = novoNode;
                this.qtd++;
            } else if (novoNode.getInfo().compareTo(this.ultimo.getInfo()) > 0) { // inserção após o último
                NodeCategoria ant = this.ultimo;
                novoNode.setProx(this.primeiro);
                this.primeiro.setAnt(novoNode);
                novoNode.setAnt(ant);
                ant.setProx(novoNode);
                this.ultimo = novoNode;
                this.qtd++;
            } else { // inserção no meio - depois do primeiro e antes do último
                NodeCategoria aux = this.primeiro;
                do {
                    if (aux.getInfo().compareTo(novoNode.getInfo()) > 0) {
                        // System.out.println("Filme já cadastrado. Inserção não efetuada!");
                        break;
                    } else {
                        aux = aux.getProx();
                    }
                } while (aux != this.primeiro);
                NodeCategoria anterior = aux.getAnt();
                anterior.setProx(novoNode);
                novoNode.setAnt(anterior);
                novoNode.setProx(aux);
                aux.setAnt(novoNode);
                this.qtd++;
                System.out.println("Filme inserido com sucesso!!");
            }
        }
    }

    public void remover(String titulo) {
        if (this.isEmpty()) {
            System.out.println("Lista vazia!");
        } else {
            NodeCategoria buscado = buscaMelhorada(titulo);
            if (buscado != null) {
                if (this.qtd == 1) {
                    this.primeiro = null;
                    this.ultimo = null;
                    this.qtd = 0;
                } else if (buscado == this.primeiro) {
                    this.primeiro = this.primeiro.getProx();
                    this.primeiro.setAnt(this.ultimo);
                    this.ultimo.setProx(this.primeiro);
                    this.qtd--;
                } else if (buscado == this.ultimo) {
                    this.ultimo = this.ultimo.getAnt();
                    this.ultimo.setProx(this.primeiro);
                    this.primeiro.setAnt(this.ultimo);
                    this.qtd--;
                } else {
                    NodeCategoria aux = this.primeiro;
                    while (aux.getProx() != buscado) {
                        aux = aux.getProx();
                    }
                    NodeCategoria prox = buscado.getProx();
                    aux.setProx(prox);
                    prox.setAnt(aux);
                    this.qtd--;
                }
            }
        }
    }
}