from flask import Flask, render_template
from flask_apscheduler import APScheduler

app = Flask(__name__)

a = 5;
@app.route('/', methods=['GET'])
def hello():

    with open("data/incorrect_answers.txt", "rw+") as file:
        incorrect_answers = file.readlines()

    return render_template('home.html',something=incorrect_answers[-1])

if __name__ == '__main__':
    #scheduler = APScheduler()
    #scheduler.add_job(func=hello,args=['job run'],trigger='interval',id='job', seconds=5)
    #scheduler.start()
    app.run(debug=True,host='0.0.0.0',port = 5050,threaded=True)
