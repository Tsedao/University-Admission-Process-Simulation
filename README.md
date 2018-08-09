# University Admission Process Simulation
## Introduction
Around the world, many colleges and universities receive thousands of applications for freshman admission every
year. The challenge of many universities is to select students who are most likely succeed in college and make a
significant contribution to their field of study accurately and quickly. Research in college enrollment has been
limited to the study of three factors: a student’s application decisions, institution’s acceptance decisions, and
college choice. A typical university application packet comprises of transcripts, standardized test scores, let-
ters of recommendation, a statement of purpose that expresses student’s aims, ambitions and research interests,
and descriptive answers to a few additional questions. Test scores include GRE, language test scores - such as
TOEFL or IELTS etc. Universities, then, evaluate application packets based on rules or heuristics which are
unknown to students, and release decisions whether the student will be accepted or rejected and whether he
or she reach the scholarship requirements. This whole process is called University Admission Process. To
facilitate the process of evaluating each and every applicant in a relatively short amount of time, universities
and colleges focus their efforts on building the most efficient systems to help them recruit talented students,
particularly from foreign countries.

Different types of colleges and universities may face different kinds of challenges through admission process.
For instance, universities which are well-known and with good reputation may receive huge numbers of applica-
tions over their target numbers, so it is difficult for them to evaluate those applications quickly and preciously
in a short and required time. However, for private and small colleges, they may face challenges in attracting
bright students and in competing with large public universities.

In order to determine successful recruitment for colleges and universities, the project will focus on the quality
and efficiency of different universities’admission system from the universities perspective. Our goal is to build a
decision support model used computer simulation to provide pertinent information to colleges and help different
types of universities select the most suitable and efficient admission process.

## Literature Review
In Eugene’s paper, only admission of a pharmacy doctor was discussed and random forest machine learning
technique was utilized to filter these candidates preliminary. Similarly, by the means of multivariate logistic
regression, the GRADE (graduate admissions evaluator) introduced by Austin Waters and Risto Miikkulainen
is also aimed at predicting the applicants’ potential performance and enhancing the efficiency in admitting
applicants in computer science department. Unlike Eugene and Austin, Pual built an empirical probabilistic
model and used Linear Programing to decide the allocation of university based merit aid whilst maximizing the
quality of the admitted applicants. Generally, the machine learning techniques or optimization algorithms they
have utilized basically is functioned as a preliminary filter and indeed, they save time for reviewing applicants
in some extend. However, Basing on large applicants’information, these mechanism utilized only served as a
classifier and actually they does not touch the intrinsic property of a realistic university admission process.

## Methodology and Procedure

## Notations
| Symbol        | Definition  |
| --------   | ---------------------------------------:   |
| N        | The number of total applied students in this semester     |
| n        | The number of applied students per rolling       |
| M       | The number of total admitted students in this semester      |
| p       | The number of admitted students per rolling      |
| gama       | The number of rolling rounds      |