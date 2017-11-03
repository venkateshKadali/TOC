# -*- coding: utf-8 -*-
"""
Created on Sat Sep 23 15:27:04 2017

@author: venky


"""


import numpy as np
import pickle


class ANN:
    #Input array
    X=np.array([[1,0,0,0,0,0,0,0],[0,1,0,0,0,0,0,0],[0,0,1,0,0,0,0,0],[0,0,0,1,0,0,0,0],[0,0,0,0,1,0,0,0],[0,0,0,0,0,1,0,0],[0,0,0,0,0,0,1,0],[0,0,0,0,0,0,0,1]])
    
    #Output array
    y=np.array([[1,0,0,0,0,0,0,0],[0,1,0,0,0,0,0,0],[0,0,1,0,0,0,0,0],[0,0,0,1,0,0,0,0],[0,0,0,0,1,0,0,0],[0,0,0,0,0,1,0,0],[0,0,0,0,0,0,1,0],[0,0,0,0,0,0,0,1]])
        
    #Sigmoid Function
    def sigmoid (self,x):
        return 1/(1 + np.exp(-x))
    
    #Derivative of Sigmoid Function
    def derivatives_sigmoid(self,x):
        return x * (1 - x)    
    
    def build(self):
        #constructs the three layer network
        self.lr=0.1 #Setting learning rate
        input_neurons = self.X.shape[1] #number of columns in data set
        hidden_neurons = 3 #number of hidden layers neurons
        output_neurons = self.y.shape[1] #number of output layer neurons
        
        #weight and bias initialization
        self.hidden_weight=np.random.uniform(size=(input_neurons,hidden_neurons))
        self.hidden_bias=np.random.uniform(size=(1,hidden_neurons))
        self.output_weight=np.random.uniform(size=(hidden_neurons,output_neurons))
        self.output_bias=np.random.uniform(size=(1,output_neurons))
    
    def train(self, numIterations):
        self.numIterations = numIterations
        self.propagation(self.numIterations)
    
    def fit(self,z):
        self.z = z
        return self.forward_propagation(z)
        
    #propagation funciton consists of forward and backward propagation which takes number of iterations as an argument 
    def propagation(self,numIterations):
        self.numIterations = numIterations
        for i in range(numIterations):
            self.forward_propagation(self.X)    
            self.backward_propagation()
    
    
    def forward_propagation(self, X):
        #Forward Propogation
        self.hidden_layer_input1=np.dot(X,self.hidden_weight)
        self.hidden_layer_input=self.hidden_layer_input1 + self.hidden_bias
        self.hiddenlayer_activations = self.sigmoid(self.hidden_layer_input)
        self.output_layer_input1=np.dot(self.hiddenlayer_activations,self.output_weight)
        self.output_layer_input= self.output_layer_input1+ self.output_bias
        self.output = self.sigmoid(self.output_layer_input)
        return self.output
    
    
    def backward_propagation(self):
        #Backpropagation
        E = self.y-self.output
        self.slope_output_layer = self.derivatives_sigmoid(self.output)
        self.slope_hidden_layer = self.derivatives_sigmoid(self.hiddenlayer_activations)
        self.d_output = E * self.slope_output_layer
        self.Error_at_hidden_layer = self.d_output.dot(self.output_weight.T)
        self.d_hiddenlayer = self.Error_at_hidden_layer * self.slope_hidden_layer
        self.output_weight += self.hiddenlayer_activations.T.dot(self.d_output) *self.lr
        self.output_bias += np.sum(self.d_output, axis=0,keepdims=True) *self.lr
        self.hidden_weight += self.X.T.dot(self.d_hiddenlayer) *self.lr
        self.hidden_bias += np.sum(self.d_hiddenlayer, axis=0,keepdims=True) *self.lr
    
    #save function saves the output object in an output file. It uses pickle library to dump the object in an output file.
    def save(self):    
        filename = "output"
        fileObject = open(filename,'wb')
        pickle.dump(self, fileObject)
        fileObject.close()
    #restores the object from the output file and prints the data on the console.
    def restore(self):
        with open("output", "rb") as data:
            return pickle.load(data) 
#    print (output)
#created an object ann for the class ANN    
ann = ANN()    
ann.build()
ann.train(5000)
#input array to the fit method for testing.
z = np.array([0,0,0,0,0,0,0,1])
#fit fucntion takes the given input array and returns the forward propagation output 
forward_propagation_output = ann.fit(z)
print(forward_propagation_output)
#dumps the output object into an output file
ann.save()    
#restores the data from the output file.
k = ann.restore()
print(k.fit(z))