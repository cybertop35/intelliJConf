package com.marco.tutorial

class Complex(real:Double, imginary:Double) {
    def re() = real;
    def im() = imginary;
    
    override def toString() = "" + re +(if (im<0) "" else "+" ) + im + "i";

}