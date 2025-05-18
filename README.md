<h1> Zilla Order</h1>

Architecture
![image](https://github.com/user-attachments/assets/f06f50c9-b250-42fb-9350-7f1670614027)


<h2>Highlights</h2>
<ul>
  <li> Integrated test with test containers</li>
  <li> MIcroservices </li>
  <li> NoSql Databases </li>
</ul>

<h2>Sinc comunication between services:</h2>
![image](https://github.com/user-attachments/assets/91522ca7-95d3-437a-8c22-dbd88c5bbeee)

Update 1.0 - Discovery Service
After the single services are ready and operating it's working like this:
![image](https://github.com/user-attachments/assets/57023b10-04e6-4cbe-94ca-7ffdfb133095)

This is problematic because we have the endpoint hardcoded. We can have multiple instnces of a Service and each instance can have a dynamic ip addres. This way, how our service will know which service to call ? 
To solcew this issue we are going to apply the service Discovery pattern
![image](https://github.com/user-attachments/assets/ef805bf9-eef4-4c4e-9262-886499a45ba8)

