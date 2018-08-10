# University Admission Process Simulation
## 1. Introduction
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

## 2. Literature Review
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

## 3. Procedure and Methodology
Firstly, barinstorming and online search was both done to figure out what kinds of admission strategy are popularly utilized currently by nowdays university. Then, flowchart related to the most popular utilized university admission strategy was drawn. Then, based on the flowchart, relevant psuedo-code was also perpared. After scrutiny of the logic correctness of the psuedo-code, it is then implemented by JAVA. During the implementation, each experiment was repliced 500 times. Then, in order to optimize the quality and efficiency of the admission strategy, average score of admitted applicants and average waiting time of admitted applicants was output through the simulation. Meanwhile, the status of each server in the system is also recorded. Finally, basic sensitivity analysis for the parameter was done and some suggestion on the admission process was also given. 


## 4. Notations
| Symbol        | Definition  |
| --------   | ---------------------------------------:   |
| N        | The number of total applied students in this semester     |
| n        | The number of applied students per rolling       |
| M       | The number of total admitted students in this semester      |
| p       | The number of admitted students per rolling      |
| \gamma       | The number of rolling rounds      |

## 5. Asumptions
 1.	The admission process is only suit for graduate program.
 2.	Each applicant will be checked Transcripts (GPA), Standardized test scores (GRE/GMAT, language test scores - such as TOEFL or IELTS), Letters of recommendation
 and A statement of purpose (expresses student’s aims, ambitions and research interests, and descriptive answers to a few additional questions) in the simulation of admission process.
 3. All the applicants have finished bachelors’ degree or any equivalent degree.
 4.	The university will not admit students who submit the applications after the deadline.
 5.	Every applicant was assumed to apply for the scholarship, and the scholarship requirement is the same for all applicants.
 6. Offers sent in the final roll all will not included scholarship
 7. In rolling admission, if the time is after the application deadline, then no matter whether the number of applicants in applicant pool has reach the expected number (n) or not, we will start the second round review, which is the ranking procedure.

## 6. Data-preprocessing 
From university’s perspective, normally the approximate applications in this year can be estimated based on empirical experience and the approximate admitted applicants in this year is manually controlled by the university. Therefore, in our university admission process, the number of total applied students in this year (N) and the number of total admitted applicants (M) can be known in advance. Consequently, what we need to set up for the admission process is the number of applied students per rolling (n) and the number of admitted applicants in each rolling (p). Since all the N applicants must be reviewed at the end of the lasting rolling and for the “maintain the whole waitlist” strategy, actually only p applicants have entered one rolling after the end of the first rolling. Therefore, we can build an equation related with n and p base on the number of rolling:

![equation1](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/equation1.png)

Considering the rest n-p applicants may be the worst n-p applicants among the whole N applicants and occupy the pool’s position forever, we have modified this strategy by dropping the last 50% applicants from the rest of the applicants’ pool, that is, about p+1/2(n-p) new applicants will enter this rolling. Consequently, Equation 1 has changed to:

![equation2](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/equation2.png)

## 7. Simulation and Analysis 
In this part, we only focus on one university and the related parameters have been set. Then base on the Equation updated above, we generate different combinations of n-p values. For different combinations of n-p values, we compare their performance through the admitted applicants’ comprehensive score and the average wait time of each applicants. Then we choose one base case of rolling admission to analyse:
 * The changing trend of duration from the first rolling to the end rolling.
 * The changing trend of admitted applicants’ comprehensive score from the first rolling to the end rolling.
 * The status of the two servers in the system.

### 7.1. Parameter settings

To make the artificial dataset more close to the real situation, we have designed a dataset such that: in the early period of the admission process, applicants' quality is not very high and they are handing their application very slowly, however, when it closed to the end the admission process, applicants are urgent to apply for the university and also their profiles is very good. Therefore, here we assume the score of applicants will grow in a trend like logarithm and the inter-arrival time of each applicant will decrease in a negative exponential trend. More detials will be shown in the below table.


| Attribute        | Probability Distribution |Parameters|
| --------   |---------------:   |--------------:   |
| Inter-arrival time   |Exponential Distribution   |Mean: 6e^(-k*i*)+2  |
| Native/Non native speaker   |Bernoulli Distribution   |P(Native)=0.6 |
| GPA   |Normal Distribution   |Mean: 70 + log(*i*), Var:100 |
| Standard test scores   |Normal Distribution  |Mean: 70 + log(*i*), Var:100|
| Level of recommendation letter   |Normal Distribution  |Mean: 70 + log(*i*), Var:100|
| Level of personal statement   |Normal Distribution  |Mean: 70 + log(*i*), Var:100|
| Service time of server 1  |Exponetial Distribution  |Mean: 1|
| Service time of server 2   |Exponetial Distribution  |Mean: 1.5|
| University’s minimum requirement for GPA | NONE   | 60  |
| University’s minimum requirement for standardized test score   | NONE   | 60  |
| The number of total applied students in this semester(N) | NONE   | 1000   |
| The number of total admitted students in this semester(M) | NONE   | 200    |

**Note**: *i* here denotes the order of applicants in arrival time  

After the generation of all these 1000 applicants, the dataset is kept for all the time. Meanwhile, the figure illustrated the changing trend of score and inter-arrival time is shown below. 

![app_profile](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/profile_apps.jpg)

### 7.2. Comprehensive performance analysis

Here we choose 5 groups of combinations of n-p, that is (n=205, p=45), (400,30), (635,45), (800,45) and (1000,80) to examine there performance by average score and average wait time of all the applicants.

![np](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/n_score_time_hasrejection.jpg)

We see that both average score and average wait time will increase as n increases. However, the average score only raises less than 4 points from n=405 to n=1000 while the average waiting time has expanded nearly about 100%. 

### 7.3. Rolling performance analysis

Here we choose (n=405, p=50) as a base case to analyse how to enhance the quality and admitted students. Consequently, we have considered whether changing the number of admitted applicants per rolling will affect the final performance or not. Moreover, we also check which rolling round in the busiest period so that we put more people there to reduce time. First, the origin information of the four rollings with constant p value is shown below:

| |Start time (hours) |End time (hours) |Duration
(hours) | Avg score (admitted)|
|--------|--------:|--------:|--------:|--------:|
|1st rolling|0|2530|2530|92.3|
|2rd rolling|2527|3292|765|91.8|
|3th rolling|	3286|	4033|	747|94.0|
|4th rolling|4027|	4422|	395	|90.4|
|Re rolling	|4422|	4422|	0	|90.4|

Because in every rolling except the first one, there must be some applicants which are from the previous the round keep occupying the position, it's not fair to always give these applicants the same oppotunity as that of the new-arrived applicants later. Therefore, we decide to curtail the quota of admitted applicants per rolling. To choose these new p values in a wise way, we decide to make the value of p proportional to the number of new-arrived applicants for each rolling. That is:

| |Number of new arrived applicants|p value|
|--------|:--------:|:--------:|
|1st rolling|400|80|
|2rd rolling |225|45|
|3th rolling|225|45|
|4th rolling|150|30|

After changing the p values per rolling, we can see from the below figure that, although in the first rolling, the quality seems to be not good as before, almost for all the rest of the rolling rounds, the admitted applicants is better than constant p.

![dynamic_p](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/change_p_vs_not.jpg)

#### 7.3.1. Server 1
||Round 1 |Round 2|Round 3|Round 4|Average|
|----|:----:|:----:|:----:|:----:|:----:|
|Avg waiting time/h|	0.349|	0.673|	0.589|	0.936|	0.561|
|Avg queueing length	|0.055|0.189|	0.178|	0.402|	0.127|
|Busy rate|	0.152|	0.348|	0.306|	0.340|	0.227|

![queue1](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/queue1.png)

![idle1](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/idle1.png)

#### 7.3.2. Server 2
||Round 1 |Round 2|Round 3|Round 4|Average|
|----|:----:|:----:|:----:|:----:|:----:|
|Avg waiting time/h|	0.623|	1.741|	1.498|	1.228|	1.129|
|Avg queueing length	|0.098|0.486|	0.451|	0.523|	0.256|
|Busy rate|	0.221|	0.551|	0.491|	0.488|	0.345|

![queue2](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/queue2.png)

![idle2](https://github.com/Tsedao/University-Admission-Process-Simulation/raw/master/graphs_and_tables/idle2.png)

## 8. Suggestion and conclusion 
From the results shown above, it can be concluded that both rolling admission and normal admission has its advantage and disvantage. From university prospective, if raising the quality of the admitted students is their first priority, then, normal admission definitely is a good choice to them. However, for university that wants to respond to the applicants very soon, normal admission will be the last choice. Importantly, based on the total number of applied students from previous years, the university should not allow to much students in one rolling. Specifically, one of the choice is to let the maximum capacity for each rolling equal to 40% - 60% of the total number of applied students. Additionally, for the university that wants to try rolling admission strategy, adjusting the number of admitted students per rolling will be able to help university admit better applicants. Moreover, to raise the efficiency of the rolling admission, it is also suggested that the admission department should put more labor force in the middle round of the rolling process. Beacuse, during the middle peroid of the rolling process, it always turns out more busy than the other period. 
