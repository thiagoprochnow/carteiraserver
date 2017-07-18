'use strict';

const AWS = require('aws-sdk'); // eslint-disable-line import/no-extraneous-dependencies

const ddb = new AWS.DynamoDB.DocumentClient();
var dbParams = {
    TableName: process.env.DYNAMODB_TABLE_STOCK_EV,

}

module.exports.createEvent = (event, context, callback) => {

    const timeStamp = new Date().getTime();
    var data = JSON.parse(event.body);

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
            return;
        }

        // no error, let´s respond ok
        const response = {
            statusCode: 200,
            body: JSON.stringify(dbParams.Item),
        };
        callback(null, response);
    });
};
