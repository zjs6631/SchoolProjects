# P6 - Car Wash
# Queue Simulation
# [Zachary Smith]

from gettext import install


class Events:
    class RtnEvent:     #  need to return multiple values when popping an item from the EventQ
        def __init__(self, custNum, timeStamp, eventType):
            self.custNum = custNum
            self.timeStamp = timeStamp
            self.eventType = eventType

    def __init__(self, nEvents):
        self.frontPtr = 1   #oldest item in queue
        self.rearPtr = 0    #newest item in queue
        self.custNum = []   # These arrays hold the event info.  The customer number identies a unique customer
        self.timeStamp = [] # Time when event is scheduled for
        self.eventType = [] # Type of event (A)rrival, Enter (W)ash queue, Enter (D)etail queue
        for n in range(nEvents):
            self.custNum.append(0)
            self.timeStamp.append(0)
            self.eventType.append(None)

    def setEV(self, custNum, timeStamp, eventType):     #use to initialize the first 25 arrivals into Event queue
        self.custNum[custNum] = custNum
        self.timeStamp[custNum] = timeStamp
        self.eventType[custNum] = eventType
        self.rearPtr +=1
    
    def insertEV(self, custNum, insTime, eventType):    # insert a new event in correct time order
    #[insert your code here]
        
        i = self.rearPtr #variable to hold rearptr value so I dont have to actually change the ptr itself
        
        while (insTime < self.timeStamp[i]) or self.timeStamp[i] == 0: #if the inserted value is less than the value at i or i == 0 (an empty space)
            tempTime = self.timeStamp[i] #use temp variables to hold the values held at the rearptr location
            tempCustNum = self.custNum[i]
            tempEventType = self.eventType[i]
            i+=1 #move forward one space
            self.custNum[i] = tempCustNum #set the next space to the values help by temp variables
            self.timeStamp[i] = tempTime
            self.eventType[i] = tempEventType
            i-=1 #move back to previous space
            self.custNum[i] = custNum #set the initial space to the values being inserted
            self.timeStamp[i] = insTime
            self.eventType[i] = eventType
            i-=1
        
        if insTime > self.timeStamp[self.rearPtr] and insTime > self.timeStamp[self.rearPtr+1]: #This accounts for the final event
            self.custNum[self.rearPtr+1] = custNum
            self.timeStamp[self.rearPtr+1] = insTime
            self.eventType[self.rearPtr+1] = eventType

        self.rearPtr+=1 #move rearptr forward one
        
        
        
    def popEV(self):                                    # remove front item from the queue.  Doest physically remove the item, just adjust frontPtr
        self.frontPtr +=1
        return Events.RtnEvent(self.custNum[self.frontPtr-1], self.timeStamp[self.frontPtr-1], self.eventType[self.frontPtr-1])

class WashQ:
    def __init__(self, nCusts):
        self.frontPtr = 1            # Oldest item in queue
        self.rearPtr = 0             # Newest item in queue
        self.nServers = 2            # number of people washing cars
        self.custNum = []            # These arrays hold the event info.  The customer number identies a unique customer       
        self.timeStamp = []          # Time when event is scheduled for
        self.serveTime = []          # Time when waiting ended and service began
        self.waiting = []            # Indicate cars on WashQ waiting to get washed
        for n in range(nCusts):
            self.custNum.append(0)
            self.timeStamp.append(0)
            self.serveTime.append(0)
            self.waiting.append("No")

    def addQ(self, custNum, timeStamp): # Add a car to the rear of WashQ
        #[insert your code here]
        self.rearPtr+=1
        self.custNum[self.rearPtr] = custNum #current rearptr set to new item
        self.timeStamp[self.rearPtr] = timeStamp #current rearptr set to new item
        
        
    def popQ(self, currTime):           # Remove a car from the front of WashQ & return custNum of car that was popped
        #[insert your code here]
        self.frontPtr+=1 #move ptr foward once
        self.serveTime[self.frontPtr-1] = currTime #set servetime of current item to currtime from prev item
        return self.custNum[self.frontPtr-1]
    def qBusy(self):                    # Return True or False is there's a spot open in WashQ for this car
        #[insert your code here]
        front = self.frontPtr #if the difference between the two pointers in washQ greater than 2 make the car wait
        back = self.rearPtr
        if (back-front) >= 2:
            return True
        else:
            return False
    def qService(self):                 # after a car is popped from queue, move next car waiting in line to be washed
        ptr = self.frontPtr             # start from front of que
        while ptr <= self.rearPtr:      # loop until next car waiting is found
            if (self.waiting[ptr] == "Yes"):    # change status from waiting to not waiting
                self.waiting[ptr] = "No"
                return self.custNum[ptr]
            ptr +=1
        return 0

class DetailQ:
    def __init__(self, nCusts):     
        self.frontPtr = 1           # Oldest item in queue
        self.rearPtr = 0            # Newest item in queue
        self.nServers = 1           # number of people detailing cars
        self.custNum = []           # These arrays hold the event info.  The customer number identies a unique customer 
        self.timeStamp = []         # Time when event is scheduled for
        self.serveTime = []         # Time when waiting ended and service began
        self.waiting = []           # Indicate cars on DetailQ waiting to get washed
        for n in range(nCusts):
            self.custNum.append(0)
            self.timeStamp.append(0)
            self.serveTime.append(0)
            self.waiting.append("No")

    def addQ(self, custNum, timeStamp): # Add a car to the rear of DetailQ
        #[insert your code here]
        self.rearPtr+=1 #same as WashQ 
        self.custNum[self.rearPtr] = custNum
        self.timeStamp[self.rearPtr] = timeStamp
    def popQ(self, currTime):        # Remove a car from the front of DetailQ and return custNum of car that was popped
        #[insert your code here]
        self.frontPtr+=1 #same as WashQ
        self.serveTime[self.frontPtr-1] = currTime
        return self.custNum[self.frontPtr-1]
    def qBusy(self):                # Return True or False is there's a spot open in WashQ for this car
        #[insert your code here]
        front = self.frontPtr #same as WashQ except if difference greater than 1
        back = self.rearPtr
        if (back-front) >= 1:
            return True
        else:
            return False
    def qService(self):             # after a car is popped from queue, move next car waiting in line to be washed
        ptr = self.frontPtr         # start from front of que
        while ptr <= self.rearPtr:  # loop until next car waiting is found
            if (self.waiting[ptr] == "Yes"):     # change status from waiting to not waiting
                self.waiting[ptr] = "No"
                return self.custNum[ptr]
            ptr +=1
        return 0

def convertTime(minutes, offset):   # Take minutes from starting time and return a time
    if (minutes == 0):              # ie. 61 returns 10:01 AM (61 minutes from starting time of 9AM)
        return "         "
    seconds = (minutes+offset) * 60
    min, sec = divmod(seconds, 60)
    hour, min = divmod(min, 60)

    if (hour >= 12):
        postFix = "PM"
        if (hour > 12):
            hour -=12
    else:
        postFix = "AM"

    return "%d:%02d%3s" % (hour, min, postFix)

def main():
    from random import randint

    nCusts = 25             # use parameters so we can change simulation easily
    nEvents = nCusts * 3
    totMinutes = 60 * 8     # 8 hours (9AM - 5PM)
    openTime = 60 * 9 # 9AM (9 hours from midnight to 9AM)
    washMinutes = 30
    detailMinutes = 90
    pctDetail = 20

    custList = list()
    sortedCust = list()

    for cust in range(nCusts):
        arrivalTime = randint(0, totMinutes)
        custList.append(arrivalTime)
    custList.sort()                     # need to convert to time order so sort is required

    # **debug** uncomment the following line if you want to check your work against assignment output
    custList = [18,  19 ,34 ,37 ,128 ,164 ,166 ,173 ,205 ,249 ,270 ,304 ,308 ,334 ,350 ,362 ,370 ,372 ,418 ,422 ,466 ,468 ,497 ,502 ,504]

    EV = Events(nEvents)    # create single instances
    WQ = WashQ(nCusts+1)
    DQ = DetailQ(nCusts+1)

    n = 0
    for custArrTime in custList:
        n +=1
        EV.setEV(n, custArrTime, "A")       # initial arrival events put in queue

    thisCust = EV.popEV()       # process first event
    while (EV.frontPtr <= EV.rearPtr+1):  # keep going till event array is empty
        currTime = thisCust.timeStamp   # current time is when next event in queue is scheduled

        if (thisCust.eventType == "A"): # Arrival event
            WQ.addQ(thisCust.custNum, currTime) # Goes directly to WashQ
            if(WQ.qBusy() == True):     # If there are no washers available, mark it as waiting
                WQ.waiting[WQ.rearPtr] = "Yes"
            else:                       # else create an event for when washing will be done and add to Events queue
                EV.insertEV(thisCust.custNum, currTime+washMinutes, "W")

        elif(thisCust.eventType == "W"):    # Wash event
            WQcustNum = WQ.popQ(currTime)   # Pop the oldest car in WashQ.  It just got completed for a wash
            #if (randint(1, 100) <= pctDetail):  # 80/20 distribution to determine if detailing required
            # **debug** uncomment the following line and comment previous line if you want to check your work against assignment output
            if (WQcustNum == 9 or WQcustNum == 15 or WQcustNum == 19 or WQcustNum == 20 or WQcustNum == 25):
                DQ.addQ(WQcustNum, currTime)   #   one of 20% that gets detailed, gets put on Detail queue
                if (DQ.qBusy() == True):       #   If no detailers available, mark it as waiting 
                    DQ.waiting[DQ.rearPtr] = "Yes"
                else:                          # else create an event for when detailing will be done and add to events queue
                    EV.insertEV(WQcustNum, currTime+detailMinutes, "D")

            cn = WQ.qService()              #  see if a car was waiting for washing in WashQ
            if (cn > 0):                    # if it was create an event for 30 minutes from now to pop from WashQ
                EV.insertEV(cn, currTime+washMinutes, "W")

        elif(thisCust.eventType == "D"):    # Detailing Event
            DQ.popQ(currTime)               # Detailing for this car complete
            cn = DQ.qService()              # see if a car was waiting for detailing in detail queue
            if (cn > 0):                    # if it was create an event 90 minutes from now to pop from DetailQ
                EV.insertEV(cn, currTime+detailMinutes, "D")   

        thisCust = EV.popEV()

# Print Simulation Stats

    print ("Customer | Arrive-Time | Wash-Complete | Wash-Wait | Detail-Complete | Detail Wait")
    totWashWait = 0
    totDetailWait = 0
    maxWashWait = 0
    maxDetailWait = 0
    nDetails = 0

    for n in range(1, nCusts+1):
        customer = WQ.custNum[n]
        arrive = WQ.timeStamp[n]
        finishWash = WQ.serveTime[n]
        try:
            detailed = DQ.custNum.index(customer)
            finishDetail = DQ.serveTime[detailed]
        except ValueError as e:
            finishDetail = 0
        washWait = finishWash - arrive - washMinutes
        if (finishDetail > 0):
            detailWait = finishDetail - finishWash - detailMinutes
            nDetails +=1
        else:
            detailWait = 0
            
        print('{:>8d}{:>10s}{:>14s}{:>15d}{:>16s}{:>15d} '.format(customer, convertTime(arrive,openTime), convertTime(finishWash,openTime), washWait, convertTime(finishDetail,openTime), detailWait)  )
        totWashWait += washWait
        totDetailWait += detailWait
        if (washWait > maxWashWait):
            maxWashWait = washWait
        if (detailWait > maxDetailWait):
            maxDetailWait = detailWait
    print ("\nAverage Wait for Wash: %0.2f Minutes" % (totWashWait / nCusts) )
    print ("Average Wait for Detailing: %0.2f Minutes" %  (totDetailWait / nDetails))
    print ("Longest Wait for Wash: ", maxWashWait, " Minutes")
    print ("Longest Wait for Detailing: ", maxDetailWait, " Minutes")
       
if __name__ == "__main__":
    main()