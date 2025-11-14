package com.rpg.infrastructure;

public final class EmailTemplates {

    private EmailTemplates() {}

    public static String codigoVerificacaoHtml(String codigo, int minutosTtl) {
        // HTML inline-friendly para maioria dos provedores
        return """
        <!doctype html>
        <html lang="pt-BR">
        <head>
          <meta charset="utf-8"/>
          <meta name="viewport" content="width=device-width, initial-scale=1"/>
          <title>Código de Verificação</title>
        </head>
        <body style="margin:0;padding:0;background:#0e0d10;font-family:Arial,Helvetica,sans-serif;color:#f4f4f5;">
          <table role="presentation" width="100%%" cellspacing="0" cellpadding="0" style="background:#0e0d10;">
            <tr>
              <td align="center" style="padding:32px 16px;">
                <table role="presentation" width="100%%" cellspacing="0" cellpadding="0" 
                       style="max-width:560px;background:#16151a;border-radius:16px;box-shadow:0 6px 24px rgba(0,0,0,.4);">
                  <tr>
                    <td style="padding:28px 28px 0 28px;" align="center">
                      <div style="font-size:20px;letter-spacing:.5px;color:#d4d4d8;">CODEX RPG</div>
                      <div style="font-size:28px;margin-top:4px;color:#fff;font-weight:bold;">Seu código de verificação</div>
                    </td>
                  </tr>
                  <tr>
                    <td style="padding:20px 28px 0 28px;">
                      <p style="margin:0;color:#e4e4e7;line-height:1.6;font-size:15px;">
                        Use o código abaixo para continuar o processo de redefinição/validação. 
                        Ele expira em <strong>%d minutos</strong>.
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td align="center" style="padding:22px 28px 10px 28px;">
                      <div style="display:inline-block;background:#0b7285;padding:16px 28px;border-radius:14px;
                                  font-size:32px;letter-spacing:6px;color:#fff;font-weight:700;
                                  font-family:Consolas,Monaco,monospace;border:1px solid #0ea5b6;">
                        %s
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td style="padding:8px 28px 24px 28px;">
                      <p style="margin:0;color:#a1a1aa;font-size:13px;line-height:1.6;">
                        Se você não solicitou este código, pode ignorar este e-mail com segurança.
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td align="center" style="padding:0 28px 28px 28px;color:#71717a;font-size:12px;">
                      © %s • CODEX RPG — Todos os direitos reservados
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </body>
        </html>
        """.formatted(minutosTtl, codigo, java.time.Year.now());
    }
}
