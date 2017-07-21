'use strict';

const stocks = require('./libs/stocks');

module.exports.stocksCreateEvent = (event, context, callback) => {

    if (event.body !== '') {
        callback(new Error('Body need contains item as JSON'));
            return;
    }

    stocks.createEvent(JSON.parse(event.body), (error, result) => {
        if (error) {
            console.error(error);
            callback(error);
            return;
        }

        // no error, so give back result
        const response = {
            statusCode: 200,
            body: result,
        };
        callback(null, response);
    });
};

module.exports.stocksListAllEvents = (event, context, callback) => {
    stocks.listEvents( (error, result) => {
        if (error) {
            console.error(error);
            callback(error);
            return;
        } 

        const response = {
            statusCode: 200,
            body: result,
        };
        callback(null, response);
    });
};

module.exports.hello = (event, context, callback) => {
  const response = {
    statusCode: 200,
    body: JSON.stringify({
      message: 'Go Serverless v1.0! Your function executed successfully!',
      input: event,
    }),
  };

  callback(null, response);

  // Use this code if you don't use the http event with the LAMBDA-PROXY integration
  // callback(null, { message: 'Go Serverless v1.0! Your function executed successfully!', event });
};
