package com.lijian.test;


import lombok.Data;

@Data
    public class Point {
        public final int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


