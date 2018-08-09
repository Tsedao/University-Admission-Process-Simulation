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
| \gamma       | The number of rolling rounds      |

## Asumptions
 1.	The admission process is only suit for graduate program.
 2.	Each applicant will be checked Transcripts (GPA), Standardized test scores (GRE/GMAT, language test scores - such as TOEFL or IELTS), Letters of recommendation
 and A statement of purpose (expresses student’s aims, ambitions and research interests, and descriptive answers to a few additional questions) in the simulation of admission process.
 3. All the applicants have finished bachelors’ degree or any equivalent degree.
 4.	The university will not admit students who submit the applications after the deadline.
 5.	Every applicant was assumed to apply for the scholarship, and the scholarship requirement is the same for all applicants.
 6. Offers sent in the final roll all will not included scholarship
 7. In rolling admission, if the time is after the application deadline, then no matter whether the number of applicants in applicant pool has reach the expected number (n) or not, we will start the second round review, which is the ranking procedure.

## Data-preprocessing 
From university’s perspective, normally the approximate applications in this year can be estimated based on empirical experience and the approximate admitted applicants in this year is manually controlled by the university. Therefore, in our university admission process, the number of total applied students in this year (N) and the number of total admitted applicants (M) can be known in advance. Consequently, what we need to set up for the admission process is the number of applied students per rolling (n) and the number of admitted applicants in each rolling (p). Since all the N applicants must be reviewed at the end of the lasting rolling and for the “maintain the whole waitlist” strategy, actually only p applicants have entered one rolling after the end of the first rolling. Therefore, we can build an equation related with n and p base on the number of rolling:

![equation1](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/equation1.png)

Considering the rest n-p applicants may be the worst n-p applicants among the whole N applicants and occupy the pool’s position forever, we have modified this strategy by dropping the last 50% applicants from the rest of the applicants’ pool, that is, about p+1/2(n-p) new applicants will enter this rolling. Consequently, Equation 1 has changed to:

![equation2](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/equation2.png)

## Simulation and Analysis 
In this part, we only focus on one university and the related parameters have been set. Then base on the Equation updated above, we generate different combinations of n-p values. For different combinations of n-p values, we compare their performance through the admitted applicants’ comprehensive score and the average wait time of each applicants. Then we choose one base case of rolling admission to analyse:
 * The changing trend of duration from the first rolling to the end rolling.
 * The changing trend of admitted applicants’ comprehensive score from the first rolling to the end rolling.
 * The status of the two servers in the system.

### Parameter settings

To make the artificial dataset more close to the real situation, we have designed a dataset such that: in the early period of the admission process, applicants' quality is not very high and they are handing their application very slowly, however, when it closed to the end the admission process, applicants are urgent to apply for the university and also their profiles is very good. Therefore, here we assume the score of applicants will grow in a trend like logarithm and the inter-arrival time of each applicant will decrease in a negative exponential trend. More detials will be shown in the below table.


| Attribute        | Probability Distribution |Parameters|
| --------   |---------------:   |--------------:   |
| Inter-arrival time   |Exponential Distribution   |Mean: 6e^(-k*i*)+2  |
| Native/Non native speaker   |Bernoulli Distribution   |P(Native)=0.6 |
| GPA   |Normal Distribution   |Mean: 70 + log(*i*), Var:100 |
| Standard test scores   |Normal Distribution  |Mean: 70 + log(*i*), Var:100|
| Level of recommendation letter   |Normal Distribution  |Mean: 70 + log(*i*), Var:100|
| Level of personal statement   |Normal Distribution  |Mean: 70 + log(*i*), Var:100|
| University’s minimum requirement for GPA | NONE   | 60  |
| University’s minimum requirement for standardized test score   | NONE   | 60  |
| The number of total applied students in this semester(N) | NONE   | 1000   |
| The number of total admitted students in this semester(M) | NONE   | 200    |

**Note**: *i* here denotes the order of applicants in arrival time  

After the generation of all these 1000 applicants, the dataset is kept for all the time. Meanwhile, the figure illustrated the changing trend of score and inter-arrival time is shown below. 

![app_profile](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/profile_apps.jpg)

### Comprehensive performance analysis

Here we choose 5 groups of combinations of n-p, that is (n=205, p=45), (400,30), (635,45), (800,45) and (1000,80) to examine there performance by average score and average wait time of all the applicants.

![np](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/n_score_time_hasrejection.jpg)

We see that both average score and average wait time will increase as n increases. However, the average score only raises less than 4 points from n=405 to n=1000 while the average waiting time has expanded nearly about 100%. 