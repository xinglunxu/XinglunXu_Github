#include <iostream>
#include "Record.h"
using namespace std;




Record::Record()
{
  quiz1 = 0;
  quiz2 = 0;
  midterm = 0;
  final = 0;
}

Record::Record(double quiz1,double quiz2,double midterm, double final):quiz1(quiz1),quiz2(quiz2),midterm(midterm),final(final)
{
  if(quiz1<0){quiz1=0;}else if(quiz1>10){quiz1=10;}
  if(quiz2<0){quiz2=0;}else if(quiz2>10){quiz2=10;}
  if(midterm<0){midterm=0;}else if(midterm>100){midterm=100;}
  if(final<0){final=0;}else if(final>100){final=100;}
}


char Record::letterEquiv(double grade) const{
  if (grade >= 90) return 'A';
  if (grade >= 80) return 'B';
  if (grade >= 70) return 'C';
  if (grade >= 60) return 'D';
  return 'F';
}

char Record::overallGrade() const{
  double quizPct = (quiz1 / 10 + quiz2 / 10) / 2;
  double overall = 25 * quizPct + 0.25 * midterm + 0.5 * final;
  return letterEquiv(overall);
}

char Record::overallGrade(double quizWt, double midtermWt, double finalWt)const
{
  if ((quizWt+midtermWt+finalWt)!=1)
    {
      double sum = quizWt+midtermWt+finalWt;
      quizWt = quizWt/sum;
      midtermWt = midtermWt/sum;
      finalWt = finalWt/sum;
    }
    
  double quizPct = (quiz1 / 10 + quiz2 / 10) / 2;
  double overall = quizWt * quizPct + midtermWt * midterm + finalWt * final;
  return letterEquiv(overall);
}

void Record::setGrades(double q1, double q2, double m, double f)
{
  quiz1 = q1;
  quiz2 = q2;
  midterm = m;
  final = f;
}
