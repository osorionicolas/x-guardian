exports = function() {
    /*
      A Scheduled Trigger will always call a function without arguments.
      Documentation on Triggers: https://docs.mongodb.com/realm/triggers/overview/
  
      Functions run by Triggers are run as System users and have full access to Services, Functions, and MongoDB Data.
  
      Access a mongodb service:
      const collection = context.services.get(<SERVICE_NAME>).db("db_name").collection("coll_name");
      const doc = collection.findOne({ name: "mongodb" });
  
      Note: In Atlas Triggers, the service name is defaulted to the cluster name.
  
      Call other named functions if they are defined in your application:
      const result = context.functions.execute("function_name", arg1, arg2);
  
      Access the default http client and execute a GET request:
      const response = context.http.get({ url: <URL> })
  
      Learn more about http client here: https://docs.mongodb.com/realm/functions/context/#context-http
    */
    
    const moment = require("moment");
    
    const ObjectId = (m = Math, d = Date, h = 16, s = s => m.floor(s).toString(h)) =>
      s(d.now() / 1000) + ' '.repeat(h).replace(/./g, () => s(m.random() * h));
      
    function getRandomAlertType(){
      const alerts = ["61d4e74f75ab1fd3335cfd5f", "61d4e7c975ab1fd3335cfd60", "61d4e82e75ab1fd3335cfd61", "61d4e90175ab1fd3335cfd62"];
      const random = Math.floor(Math.random() * alerts.length);
      return alerts[random];
    }
  
    const alertTypes = {
      "61d4e74f75ab1fd3335cfd5f": "outage",
      "61d4e7c975ab1fd3335cfd60": "stray",
      "61d4e82e75ab1fd3335cfd61": "crime",
      "61d4e90175ab1fd3335cfd62": "flood"
    };
    
    const alertsCollection = context.services.get("X-Guardian").db("xguardian").collection("alerts");
    const randomAlertType = getRandomAlertType();
    
    context.http.get({ url: "https://api.3geonames.org/?randomland=US&json=1" }).then(response => {
      const body = JSON.parse(response.body.text());
  
      const data = {
      _id: ObjectId(),
      alert: {
        id: randomAlertType,
        name: alertTypes[randomAlertType],
      },
      user_id: ObjectId(),
      created_at: moment().utcOffset(-3).format("YYYY-MM-DD HH:mm:ss"),
      location: {
        type: "Point",
        coordinates: [parseFloat(body.major.longt), parseFloat(body.major.latt)]
      }
    };
    
    alertsCollection.insertOne(data);
    });
    
};