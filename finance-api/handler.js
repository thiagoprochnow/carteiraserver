'use strict';

const stocks = require('./libs/stocks');

function tryParseJSON (jsonString) {
    try {
        var o = JSON.parse(jsonString);

        // Handle non-exception-throwing cases:
        // Neither JSON.parse(false) or JSON.parse(1234) throw errors, hence the type-checking,
        // but... JSON.parse(null) returns null, and typeof null === "object", 
        // so we must check for that, too. Thankfully, null is falsey, so this suffices:
        if (o && typeof o === "object") {
            return o;
        }
    }
    catch (e) { }

    return false;
};

module.exports.stocksCreateEvent = (event, context, callback) => {

    if (!event.body) {
        callback(new Error('Body need contains item as JSON'));
        return;
    }
    var jsonItem = tryParseJSON(event.body);
    if (!jsonItem) {
        callback(new Error('Body has not a valid JSON'));
        return;
    }

    stocks.createEvent(jsonItem, (error, result) => {
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

module.exports.stocksDeleteEvents = (event, context, callback) => {

    if (!event.body) {
        callback(new Error('Body need contains items as JSON'));
        return;
    }
    var jsonItem = tryParseJSON(event.body);
    if (!jsonItem) {
        callback(new Error('Body has not a valid JSON'));
        return;
    }

    stocks.deleteEvents(jsonItem, (error, result) => {
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
