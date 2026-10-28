# React 1:1 API contract
The attached frontend calls `/auth`, `/student`, `/roadmap`, `/learning`, `/interview`, `/placement`, `/recruiter`, `/notification`, and `/admin`. Gateway compatibility routes preserve those prefixes. `frontend-services-original.js` is included as the exact uploaded contract.

The generated services use full layered packages: controller, dto/request, dto/response, entity, mapper, repository, service, service/impl, exception, Flyway migration and tests. Provider-backed AI, payment, OTP, video, email/SMS, and cloud object storage still require credentials and provider-specific adapters.
