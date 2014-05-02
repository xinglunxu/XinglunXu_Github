#ifndef RECORD_H
#define RECORD_H
class Record {
 public:
  Record();
  Record(double,double,double,double);
  void setGrades(double, double, double,double);
  char overallGrade() const;
  char overallGrade(double,double,double) const;
    
  double getQuiz1(){return quiz1;};
  void setQuiz1(double quiz1){this->quiz1 = quiz1;};
  double getQuiz2(){return quiz2;};
  void setQuiz2(double quiz2){this->quiz2 = quiz2;};
  double getMidterm(){return midterm;};
  void setMidterm(double midterm){this->midterm = midterm;};
  double getFinal(){return final;};
  void setFinal(double final){this->final = final;};
    
   
 private:
  double quiz1, quiz2;
  double midterm, final;
    
  char letterEquiv( double) const;
};
#endif
