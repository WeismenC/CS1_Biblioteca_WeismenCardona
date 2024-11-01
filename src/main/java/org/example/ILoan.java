package org.example;

import org.exceptions.DataException;
import java.io.IOException;
import java.util.Scanner;

public interface ILoan {
    void registerLoan(Admin admin, Scanner scanner) throws IOException, DataException;
    void registerReturn(Admin admin, Scanner scanner) throws IOException, DataException;
    void checkHistory();
}
