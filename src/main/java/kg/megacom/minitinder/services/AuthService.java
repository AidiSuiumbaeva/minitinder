package kg.megacom.minitinder.services;

import kg.megacom.minitinder.models.dto.AuthRequest;
import kg.megacom.minitinder.models.dto.Response;
import kg.megacom.minitinder.models.enums.Language;

public interface AuthService {
    Response auth(AuthRequest request, Language language);
}
