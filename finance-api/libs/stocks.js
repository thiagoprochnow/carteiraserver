'use strict';

const AWS = require('aws-sdk'); // eslint-disable-line import/no-extraneous-dependencies

const ddb = new AWS.DynamoDB.DocumentClient();
var dbParams = {
    TableName: process.env.DYNAMODB_TABLE_STOCK_EV,
};

module.exports.createEvent = (data, callback) => {

    const timeStamp = new Date().getTime();

    if (typeof data.id !== 'string') {
        console.error('Validation Error: ID is not string');
        callback(new Error('Validation error: ID must be string'));
        return;
    }
    if (typeof data.date !== 'string') {
        console.error('Validation Error: set date to now');
        data.date = timeStamp;
    }

    data.createdAt = timeStamp;
    data.updatedAt = timeStamp;

    dbParams.Item = data;

    // write to to table
    ddb.put(dbParams, (error) => {
        // Error?
        if (error) {
            console.error(error);
            callback(new Error('Couldn\´t create event'));
        }

        // no error, let´s respond ok
        callback(null, JSON.stringify(dbParams.Item));
    });
};

module.exports.listEvents = (callback) => {

    ddb.scan(dbParams, (error, result) => {
        if (error) {
            console.error(error);
            callback(new Error('Could not fetch table'));
        }

        callback(null, JSON.stringify(result.Items));
    });
};

module.exports.deleteEvents = (data, callback) => {

//    if (typeof data.id !== 'string') {
//        console.error('Validation Error: ID is not string');
//        callback(new Error('Validation error: ID must be string'));
//        return;
//    }

    var eventsArray = [];

    for (var i = 0; i < data.length; i++) {
        var item = {
            DeleteRequest : {
                Key : {
                    'id' : data[i].id,
                    'date': data[i].date
                }
            }
        };

        eventsArray.push(item);
    }

    dbParams = {};
    dbParams['RequestItems'] = {};
    dbParams['RequestItems'][process.env.DYNAMODB_TABLE_STOCK_EV] = eventsArray;

    // write to to table
    ddb.batchWrite(dbParams, (error, data) => {
        // Error?
        if (error) {
            console.error(error);
            callback(new Error('Couldn\´t create event'));
        }

        // no error, let´s respond ok
        callback(null, JSON.stringify(data));
    });
};

