package com.example.arithmetic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arithmetic.summation.ArraySum;
import com.example.arithmetic.summation.LinkSum;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sum1(View view) {
        new ArraySum().sum1();
        //2436
        LinkSum.ListNode node1 = new LinkSum.ListNode(2);
        node1.next = new LinkSum.ListNode(4);
        node1.next.next = new LinkSum.ListNode(3);
        node1.next.next.next = new LinkSum.ListNode(6);
        //564
        LinkSum.ListNode node2 = new LinkSum.ListNode(5);
        node2.next = new LinkSum.ListNode(6);
        node2.next.next = new LinkSum.ListNode(4);
        LinkSum linkSum = new LinkSum();
        LinkSum.ListNode node = linkSum.addTwoNumbers(node1, node2);
        linkSum.getNodeVal(node);
//        LinkSum.ListNode node3 = new LinkSum().addTwoNumbers(new LinkSum.ListNode(9), new LinkSum.ListNode(5));
//        Log.e("TAG", "333: "+node3.toString() );

    }
}
