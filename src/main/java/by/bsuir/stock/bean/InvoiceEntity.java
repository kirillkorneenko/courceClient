package by.bsuir.stock.bean;

import java.sql.Date;
import java.util.Collection;
public class InvoiceEntity extends Shell {
    private int id;
    private int number;
    private Date date;
    private String viewOperation;
    private String approval;
    private Collection<CargoEntity> cargosById;
    private Collection<CargosininvoiceEntity> cargosininvoicesById;
    private UsersEntity usersByIdUser;
    private UsersEntity usersByIdEmployee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getViewOperation() {
        return viewOperation;
    }

    public void setViewOperation(String viewOperation) {
        this.viewOperation = viewOperation;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceEntity that = (InvoiceEntity) o;

        if (id != that.id) return false;
        if (number != that.number) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (viewOperation != null ? !viewOperation.equals(that.viewOperation) : that.viewOperation != null)
            return false;
        if (approval != null ? !approval.equals(that.approval) : that.approval != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (viewOperation != null ? viewOperation.hashCode() : 0);
        result = 31 * result + (approval != null ? approval.hashCode() : 0);
        return result;
    }

    public Collection<CargoEntity> getCargosById() {
        return cargosById;
    }

    public void setCargosById(Collection<CargoEntity> cargosById) {
        this.cargosById = cargosById;
    }

    public Collection<CargosininvoiceEntity> getCargosininvoicesById() {
        return cargosininvoicesById;
    }

    public void setCargosininvoicesById(Collection<CargosininvoiceEntity> cargosininvoicesById) {
        this.cargosininvoicesById = cargosininvoicesById;
    }

    public UsersEntity getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEntity usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }

    public UsersEntity getUsersByIdEmployee() {
        return usersByIdEmployee;
    }

    public void setUsersByIdEmployee(UsersEntity usersByIdEmployee) {
        this.usersByIdEmployee = usersByIdEmployee;
    }
}
