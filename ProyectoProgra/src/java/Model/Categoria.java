package Model;

public enum Categoria {
    Todos,
    Tradicional,
    Extranjera,
    Especial,
    Postres,
    Rapida,
    Natural,
    Gaseosa;

    @Override
    public String toString() {
        String hilera = "Todos";
        switch (this) {
            case Tradicional:
                hilera = "Comida Tradicional";
                break;
            case Extranjera:
                hilera = "Comida Extranjera";
                break;

            case Especial:
                hilera = "Comida Especial";
                break;

            case Rapida:
                hilera = "Comida Rapida";
                break;

            case Postres:
                hilera = "Postre";
                break;

            case Natural:
                hilera = "Bebida Natural";
                break;

            case Gaseosa:
                hilera = "Bebida Gaseosa";
                break;
        }
        return hilera;
    }
}
