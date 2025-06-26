---

# Wansati — Sisterhood for the Ambitious

---

## Overview

Wansati is a women-first professional networking and mentorship platform designed to empower ambitious women across Africa and beyond. Our platform fosters authentic connections, career growth, and salary transparency through features like swipe-based mentor/peer discovery, a community-rated job board, and AI-powered salary negotiation assistance.

---

## Features

* **Swipe-Based Matching:** Connect with mentors, peers, and mentees based on location, industry, experience, and goals.
* **Community Job Board:** Browse jobs from inclusive employers with anonymous company reviews and salary transparency.
* **Salary Negotiation Assistant:** Receive data-driven salary range suggestions and negotiation tips based on your profile.
* **Real-Time Chat:** Engage in conversations with matched mentors and peers via secure, WebSocket-based chat.
* **Privacy & Safety:** Anonymity by default, private mode browsing, and built-in reporting/blocking features.

---

## Technology Stack

* **Backend:** Spring Boot, PostgreSQL, Spring Security, STOMP/WebSockets
* **Frontend:** (In progress) Next.js
* **Authentication:** JWT-based security with role-based access controls
* **Real-Time Communication:** STOMP over WebSockets for chat features
* **Database:** PostgreSQL relational schema for user profiles, matches, skills, and more

---

## Current Status

This project is under active development. The backend is functional, with core features like user management, mentor matching, and real-time chat implemented. The frontend is planned but not yet completed.

---

## Getting Started

### Prerequisites

* Java 17+
* PostgreSQL 12+
* Maven or Gradle for build
* WebSocket client for testing chat

### Running the Backend

1. Clone the repository:
   `git clone https://github.com/yourusername/wansati.git`
2. Configure your PostgreSQL database and update `application.properties` with your credentials.
3. Build and run the Spring Boot app:
   `./mvnw spring-boot:run`
4. Access API endpoints at `http://localhost:8081/api`
5. Use a WebSocket client to connect to the chat endpoint at `ws://localhost:808 1/ws`

---

## Contributing

Contributions are welcome! Feel free to open issues or pull requests for bug fixes, feature enhancements, or documentation improvements.

---

## License

MIT License © 2025 Alison Pariela

---
