#include <iostream>
#include "Record.h"
using namespace std;

Record getScores() {
  Record result;
  int q1, q2, m, f;
  cout << "Enter scores in this order: 2 quizzes, midterm, final: ";
  cin >> q1 >> q2 >> m >> f;
  result.setGrades(q1, q2, m, f);
  return result;
}

int main() {
  Record rec = getScores();
  cout << "Grade is " << rec.overallGrade() << endl;
  return 0;
}
