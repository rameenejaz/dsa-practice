//files used are displayNode and Node3
public class displayNode {
    Node3 head;

    public void insertAtEnd(int value) {
        Node3 newNode= new Node3(value);
        if (head==null) {
            head=newNode;
            return;
        }
        Node3 temp=head;
        while(temp.next!=null) {
            temp=temp.next;
        }
        temp.next=newNode;
    }

    public void insertAtStart(int value) {
        Node3 newNode=new Node3(value);
        if (head==null) {
            head=newNode;
            return;
        }
        newNode.next=head;
        head=newNode;
    }
    public void insertAtSpecific(int position, int value) {
        Node3 newNode=new Node3(value);
        if (position==1) {
            newNode.next=head;
            head=newNode;
            return;
        }
        Node3 temp=head;
        for (int i=1; i<position-1 && temp!=null; i++) {
            temp=temp.next;
        }
        if (temp==null) {
            System.out.println("Out of range");
            return;
        }
        newNode.next=head.next;
        temp.next=newNode;
    }
    public void deleteByValue(int value) {
        if (head==null) {
            System.out.println("List is empty!");
            return;
        }
        if (head.value==value) { //if head is the one to delete
            head=head.next;
            return;
        }
        Node3 temp=head;
        while(temp!=null && temp.next.value!=value) {
            temp=temp.next;
        }
        if (temp.next==null) {
            System.out.println("Value not found");
        }
        else {
            temp.next=temp.next.next;

        }
    }
    public void display() {
        Node3 temp=head;
        while (temp!=null) {
            System.out.print(temp.value + "->");
            temp=temp.next;
        }
        System.out.println("NULL");
    }
    public static void main(String[] args) {
       displayNode list=new displayNode();
       list.insertAtEnd(10);
       list.insertAtEnd(20);
       list.insertAtEnd(30);
       System.out.print("Original Linked List:");
       list.display();

       list.insertAtStart(5);
       System.out.print("New Linked List:");
        list.display();

        list.insertAtSpecific(2, 65);
        list.display();

        list.deleteByValue(20);
        list.display();
    }
    
}
