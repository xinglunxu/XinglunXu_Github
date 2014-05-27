// vec.cpp - implements Vec3 functions
// YOUR NAME(s), DATE

#include <iostream>
#include "vec.h"
#include <string>
using namespace std;

// constructors done
Vec3::Vec3() : x(0), y(0), z(0) { }
Vec3::Vec3(double x, double y, double z) : x(x), y(y), z(z) { }
/* Do not modify the following line. If you see the message "Pass by value
   fail." in your output, it means you aren't using a reference somewhere
   that you should be. */
Vec3::Vec3(const Vec3 &other) { cout << "Pass by value fail."; }

// getters and setters done
double Vec3::get_x() const { return x; }
double Vec3::get_y() const { return y; }
double Vec3::get_z() const { return z; }
void Vec3::set_x(double value) { x = value; }
void Vec3::set_y(double value) { y = value; }
void Vec3::set_z(double value) { z = value; }

// YOUR WORK GOES HERE
ostream& operator<<(ostream& os, const Vec3& vec3)
{
  os<<"("<<vec3.get_x()<<", "<<vec3.get_y()<<", "<<vec3.get_z()<<")";
  return os;
}

istream& operator>>(istream& is, Vec3& vec3)
{
  double x, y, z;
  is>>x>>y>>z;
  vec3.set_x(x);
  vec3.set_y(y);
  vec3.set_z(z);
  return is;
}

Vec3& Vec3::operator+=(const Vec3& vec3)
{
  set_x(get_x()+vec3.get_x());
  set_y(get_y()+vec3.get_y());
  set_z(get_z()+vec3.get_z());
  return *this;
}

Vec3& Vec3::operator++(int a)
{
  set_x(get_x()+1);
  set_y(get_y()+1);
  set_z(get_z()+1);
  return *this;
}


Vec3& Vec3::operator++()
{
  set_x(get_x()+1);
  set_y(get_y()+1);
  set_z(get_z()+1);
  return *this;
}

double& Vec3::operator[](int i)
{
  if(i==0)return x;
  if(i==1)return y;
  if(i==2)return z;
}

const double& Vec3::operator[](int i) const
{
  if(i==0)return x;
  if(i==1)return y;
  if(i==2)return z;
}





