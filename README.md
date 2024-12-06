# Online Ordering System for Coffee Shops
## Overview
The Online Ordering System for Coffee Shops is an online ordering platform designed for small coffee shops. Inspired by everyday challenges faced during peak hours, this system simplifies ordering processes for both customers and coffee shop staff. By scanning a QR code, customers can place orders without interacting with staff or using in-store kiosks, ensuring faster service and operational efficiency.
## Features
### For Customers
Contactless Ordering: Place orders directly from your smartphone by scanning a QR code.

Customizable Menu: Explore available items and customize your beverages easily.

Real-time Updates: Receive notifications about the status of your order, from preparation to pickup.

Secure Payments: Pay seamlessly using integrated payment methods.

### For Coffee Shop Staff and Managers
Order Management: Track and manage incoming orders in real-time.

Inventory Tracking: Automatically update inventory based on orders.

Task Allocation: Streamline operations by assigning tasks to staff efficiently.

### For Delivery Drivers
Efficient Pickup: Locate and pick up orders easily with real-time status updates.

Improved Accuracy: Ensure correct and timely deliveries.
## Mission
The system aims to:

Reduce wait times for customers.

Provide a cost-effective, efficient solution for small coffee shops lacking resources for proprietary apps.

Enhance operational efficiency with real-time data tracking and inventory management.
## Technology Stack
### Programming Language: Java
Java is a versatile, object-oriented programming language known for its simplicity and portability. Java applications are executed on the Java Virtual Machine (JVM), allowing for "write once, run anywhere" functionality. Additionally, Java's garbage collection mechanism minimizes memory management concerns such as leaks and overflow.
### Frontend Frameworks
#### uni-app
uni-app is a versatile framework built on Vue.js, enabling developers to write a single codebase that can be deployed across platforms, including Android, Web (responsive), and various mini-program ecosystems (WeChat, Alipay, Baidu, etc.). Its rich component library, support for Vue.js syntax, and two-way data binding make it a powerful alternative to native mini-program development.
#### Vue.js
Vue.js is a progressive framework for building user interfaces based on the MVVM (Model-View-ViewModel) architecture. It combines the best features of AngularJS and React, offering a component-based structure and efficient two-way data binding. The MVVM model ensures seamless communication between the backend data (Model), the user interface (View), and the bridge connecting them (ViewModel).
### Backend Framework: Spring Boot
Spring Boot simplifies the development of Spring-based applications by providing pre-configured setups and centralized configuration management. It allows developers to focus on coding business logic without extensive configuration overhead. Custom configurations can be easily managed, while default settings handle common requirements effectively, streamlining the development process.
## System Architecture Design
The system adopts a front-end and back-end separation architecture, dividing the design into three main components: client-side (frontend), server-side (backend), and database. 
### Frontend and Backend Separation
The architecture separates the responsibilities of data processing and user interface presentation, providing the following benefits:

Decoupled Development: The frontend interacts with the backend through RESTful API interfaces and JSON data, allowing both teams to focus on their respective domains.

Enhanced Flexibility and Maintainability: By isolating frontend display logic from backend data handling, the system becomes easier to maintain, update, and extend.

Improved Efficiency: Developers can work independently, accelerating development while maintaining code quality.

Component Independence: Frontend and backend components can be optimized and updated individually, reducing costs and risks associated with system maintenance.
### Database: MySQL
The system uses MySQL as the persistent storage solution, providing:

Data Accessibility: The database design supports backend integration and enables reliable data interfaces for frontend interaction.

Administrative Ease: Administrators and operators can manage and manipulate data conveniently through backend APIs.

Reliability and Scalability: The design ensures data stability and lays a foundation for future system expansion.
## Database Design
This system uses MySQL as the database to ensure consistent data sharing and updates across the customer app, shop owner panel, and admin interface via backend APIs. The E-R model defines entities like shop owners, shops, drink categories, drinks, reviews, and orders, with relationships such as one-to-one (shop owners to shops), one-to-many (categories to drinks), and many-to-many (orders to drinks). This structure provides efficient data organization and supports seamless multi-endpoint interaction.
## Mini Program Page Design
The mini program client can be accessed by scanning a QR code, eliminating the need to download additional applications. The overall front-end interface consists of four main sections: Home, Cart, My Orders, and Profile. A navigation bar at the bottom provides access to these sections, with the icon of the current page displayed in color while others remain gray. The Home page showcases promotional information at the top, followed by a scrollable list of available beverage stores. Tapping on any store navigates to its homepage. The store homepage includes two main sections: the Ordering Page and the Comments Page. On the Ordering Page, the left panel displays beverage categories, while the right panel shows items under the selected category. These panels are interactive, and users can add items to the cart by clicking the "+" button. Once selections are complete, users can proceed to checkout. The Cart page lists all selected items, allowing users to make final adjustments before placing their order. The My Orders page displays all order details and includes an evaluation feature. Tapping the "Review" button opens a pop-up window for submitting reviews.
## PC-side Page Design
The PC-side pages for merchants and administrators share a similar design layout but differ significantly in functionality and purpose. Below are the design details for both:
### Merchant Page Design
The merchant page includes sections such as Beverages, Categories, Orders, Reviews, and Store Information, with modular tags for better organization. The content area adapts to different functionalities: list pages provide query tools with buttons at the top, while add or edit pages utilize forms with input fields and submission buttons, and detail pages display key information with relevant action buttons. 
### Admin Page Design
The administrator page features sections like Review Management, Merchant Management, and Store Management, with multi-level tags for clear categorization. The content area focuses on list displays, offering features such as querying, adding, editing, and deleting, supported by input fields and operation buttons. Both designs emphasize clarity and functionality, with additional components incorporated based on specific needs.
## Project application process display
### Web page display
![IMG_4153](https://github.com/user-attachments/assets/8cfe54a7-aa3e-401e-b27c-9478aa78a594)
![IMG_4154](https://github.com/user-attachments/assets/ead20bda-b1ed-4a53-be9f-216edb2c92f8)
![IMG_4155](https://github.com/user-attachments/assets/ce6233d3-eabe-452a-b837-f262ed44f3a3)
![IMG_4156](https://github.com/user-attachments/assets/f9d41b64-5de7-4e58-94b0-441d6fe874bd)
![IMG_4157](https://github.com/user-attachments/assets/16aaf207-b8ca-4d3f-98ad-06323940b3d5)
![IMG_4158](https://github.com/user-attachments/assets/77d11fce-d480-40d1-8691-f2522c0cc602)
![IMG_4159](https://github.com/user-attachments/assets/4169d118-57bb-49f9-82f8-5f31bcdf3361)
### Client display
![IMG_4161](https://github.com/user-attachments/assets/d2739cad-5210-4813-8fce-e1465e74e7d7)
![IMG_4162](https://github.com/user-attachments/assets/f2d4781f-7bc4-40c0-8468-da0140ac4b16)
![IMG_4163](https://github.com/user-attachments/assets/e46ea6e3-de3f-413b-9f53-343c505b6f3d)
![IMG_4164](https://github.com/user-attachments/assets/5a1d4fd0-0bcc-4ae7-8abe-62670d3b4bae)
![IMG_4167](https://github.com/user-attachments/assets/efa7357a-a6b1-4b52-a137-3d56b8fb2780)
![IMG_4168](https://github.com/user-attachments/assets/009bd715-4f67-47e2-a720-4d89abd1c7be)
![IMG_4166](https://github.com/user-attachments/assets/f315558b-a443-4f7e-83ab-8293f0515804)
