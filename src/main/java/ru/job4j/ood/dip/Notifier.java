package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Notifier {
    private SmsSender smsSender;
    private List<Client> clients = new ArrayList<>();

    public Notifier(SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void notify(String message) {
        for (Client client : clients) {
            smsSender.sendMessage(client, message);
            System.out.println("Client notified");
        }
    }

    public HashSet<Client> getAllClients() {
        return new HashSet<>(clients);
    }

}
