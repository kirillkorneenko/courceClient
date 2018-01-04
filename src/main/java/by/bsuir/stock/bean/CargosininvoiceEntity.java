package by.bsuir.stock.bean;


public class CargosininvoiceEntity extends Shell {
    private int id;
    private InvoiceEntity invoiceByNumberInvoice;
    private CargoEntity cargoByIdCargo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CargosininvoiceEntity that = (CargosininvoiceEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public InvoiceEntity getInvoiceByNumberInvoice() {
        return invoiceByNumberInvoice;
    }

    public void setInvoiceByNumberInvoice(InvoiceEntity invoiceByNumberInvoice) {
        this.invoiceByNumberInvoice = invoiceByNumberInvoice;
    }

    public CargoEntity getCargoByIdCargo() {
        return cargoByIdCargo;
    }

    public void setCargoByIdCargo(CargoEntity cargoByIdCargo) {
        this.cargoByIdCargo = cargoByIdCargo;
    }
}
