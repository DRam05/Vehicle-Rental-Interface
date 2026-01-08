public class Transactions {
    private class TransactionNode {
        Transaction data;
        TransactionNode next;

        TransactionNode(Transaction t) {
            data = t;
            next = null;
        }
    }

    private TransactionNode head;
    private TransactionNode current;

    public Transactions() {
        head = null;
        current = null;
    }

    public void add(Transaction t) {
        TransactionNode node = new TransactionNode(t);
        node.next = head;
        head = node;
    }

    public void reset() {
        current = head;
    }

    public boolean hasNext() {
        return current != null;
    }

    public Transaction getNext() {
        Transaction t = current.data;
        current = current.next;
        return t;
    }
}
