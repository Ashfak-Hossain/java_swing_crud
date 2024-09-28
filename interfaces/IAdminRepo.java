package interfaces;

import entities.Admin;

import java.util.List;

public interface IAdminRepo {
    public void addAdmin(Admin a);
    public void updateAdmin(Admin a);
    public void deleteAdmin(String id);
    public Admin searchAdmin(String id);
    public List<Admin> getAllAdmin();
}
